package cool.leeson.library.service.library;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.StringUtils;
import cool.leeson.library.config.RedisConfig;
import cool.leeson.library.dao.LibrarySeatDao;
import cool.leeson.library.entity.library.LibrarySeat;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param seatId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String seatId) {
        LibrarySeat seat = this.querySimple(seatId);
        if (seat == null) {
            log.warn(seatId + " 没有座位信息");
            return ResMap.err("没有座位信息");
        }
        return ResMap.ok(seat);
    }

    public LibrarySeat querySimple(String seatId) {
        LibrarySeat seat;
        String roomKey = String.format(RedisConfig.FormatKey.INFO.toString(), seatId);
        String s = this.redisTemplate.opsForValue().get(roomKey);

        if (StringUtils.isEmpty(s) || "".equals(s)) {
            seat = this.librarySeatDao.queryById(seatId);
            if (seat == null) {
                return null;
            } else {
                // 储存到 redis
                redisTemplate.opsForValue().set(roomKey, JSON.toJSONString(seat));
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
    public LibrarySeat insert(LibrarySeat librarySeat) {
        this.librarySeatDao.insert(librarySeat);
        return librarySeat;
    }

    /**
     * 修改数据
     *
     * @param librarySeat 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(LibrarySeat librarySeat) {
        this.librarySeatDao.update(librarySeat);
        return this.queryById(librarySeat.getSeatId());
    }

    /**
     * 通过主键删除数据
     *
     * @param seatId 主键
     * @return 是否成功
     */
    public boolean deleteById(String seatId) {
        return this.librarySeatDao.deleteById(seatId) > 0;
    }
}
