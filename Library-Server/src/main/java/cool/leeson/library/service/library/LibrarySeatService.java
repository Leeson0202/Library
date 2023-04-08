package cool.leeson.library.service.library;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.StringUtils;
import cool.leeson.library.config.RedisConfig;
import cool.leeson.library.dao.LibrarySeatDao;
import cool.leeson.library.entity.library.LibrarySeat;
import cool.leeson.library.entity.tools.RedisTool;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * (LibrarySeat)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-21 03:28:03
 */
@Service("librarySeatService")
@Slf4j
public class LibrarySeatService {
    @Resource
    private LibrarySeatDao librarySeatDao;
    @Resource
    private RedisTool redisTool;

    /**
     * 通过ID查询单条数据
     *
     * @param seatId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String seatId) {
        LibrarySeat seat = this.queryInfo(seatId);
        if (seat == null) {
            log.warn(seatId + " 没有座位信息");
            return ResMap.err("没有座位信息");
        }
        return ResMap.ok(seat);
    }

    public LibrarySeat queryInfo(String seatId) {
        LibrarySeat seat;
        String roomKey = String.format(RedisConfig.FormatKey.INFO.toString(), seatId);
        String s = redisTool.get(roomKey);

        if (StringUtils.isEmpty(s) || "".equals(s)) {
            seat = this.librarySeatDao.queryById(seatId);
            if (seat == null) {
                return null;
            } else {
                // 储存到 redis
                redisTool.set(roomKey, seat);
            }
        } else {
            seat = JSON.parseObject(s, LibrarySeat.class);
            seat = JSON.parseObject(s, LibrarySeat.class);
        }
        return seat;
    }

    /**
     * 分页查询
     *
     * @param librarySeat 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    public Page<LibrarySeat> queryByPage(LibrarySeat librarySeat, PageRequest pageRequest) {
        long total = this.librarySeatDao.count(librarySeat);
        return new PageImpl<>(this.librarySeatDao.queryAllByLimit(librarySeat, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param librarySeat 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(LibrarySeat librarySeat) {
        String seatId = UUID.randomUUID().toString();
        librarySeat.setSeatId(seatId);
        if (this.librarySeatDao.insert(librarySeat) == 0) {
            return ResMap.err();
        }
        this.redisTool.flushAll(); // 删除缓存

        return this.queryById(seatId);
    }

    /**
     * 修改数据
     *
     * @param librarySeat 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(LibrarySeat librarySeat) {
        this.librarySeatDao.update(librarySeat);
        this.redisTool.flushAll(); // 删除缓存
        return this.queryById(librarySeat.getSeatId());
    }

    /**
     * 通过主键删除数据
     *
     * @param seatId 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String seatId) {
        if (this.librarySeatDao.deleteById(seatId) > 0) {
            return ResMap.ok();
        }
        this.redisTool.flushAll(); // 删除缓存
        return ResMap.err();
    }


}
