package cool.leeson.library.service.library;

import cool.leeson.library.config.RedisConfig;
import cool.leeson.library.dao.LibraryTableDao;
import cool.leeson.library.entity.library.LibraryTable;
import cool.leeson.library.util.ResMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * (LibraryTable)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-04-07 00:01:35
 */
@Service("libraryTableService")
public class LibraryTableService {
    @Resource
    private LibraryTableDao libraryTableDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param tableId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String tableId) {
        LibraryTable libraryTable = this.libraryTableDao.queryById(tableId);
        return ResMap.ok(libraryTable);
    }

    /**
     * 分页查询
     *
     * @param libraryTable 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    public Page<LibraryTable> queryByPage(LibraryTable libraryTable, PageRequest pageRequest) {
        long total = this.libraryTableDao.count(libraryTable);
        return new PageImpl<>(this.libraryTableDao.queryAllByLimit(libraryTable, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param libraryTable 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(LibraryTable libraryTable) {
        String tableId = UUID.randomUUID().toString();
        libraryTable.setTableId(tableId);
        this.libraryTableDao.insert(libraryTable);
        this.rmCache(libraryTable.getRoomId());
        return ResMap.ok(libraryTable);
    }

    /**
     * 修改数据
     *
     * @param libraryTable 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(LibraryTable libraryTable) {
        Map<String, Object> stringObjectMap = this.queryById(libraryTable.getTableId());
        LibraryTable data = (LibraryTable) stringObjectMap.get("data");
        this.libraryTableDao.update(libraryTable);
        this.rmCache(data.getRoomId());
        return this.queryById(libraryTable.getTableId());
    }

    /**
     * 通过主键删除数据
     *
     * @param tableId 主键
     * @return 是否成功
     */

    public Map<String, Object> deleteById(String tableId) {
        LibraryTable libraryTable = this.libraryTableDao.queryById(tableId);
        if (this.libraryTableDao.deleteById(tableId) > 0) {
            return ResMap.ok();
        }
        this.rmCache(libraryTable.getRoomId());
        return ResMap.err();
    }

    private void rmCache(String roomId) {
        String roomKey = String.format(RedisConfig.FormatKey.INFO.toString(), roomId);
        redisTemplate.delete(roomKey);
    }
}
