package cool.leeson.library.service.library;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.StringUtils;
import cool.leeson.library.config.RedisConfig;
import cool.leeson.library.dao.LibraryDao;
import cool.leeson.library.dao.LibraryRoomDao;
import cool.leeson.library.dao.UserSchoolDao;
import cool.leeson.library.entity.library.Library;
import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.entity.user.UserSchool;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * (Library)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-11 13:40:59
 */
@Service("libraryService")
@Slf4j
public class LibraryService {
    @Resource
    private LibraryDao libraryDao;
    @Resource
    private LibraryRoomDao libraryRoomDao;
    @Resource
    private UserSchoolDao userSchoolDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 通过libraryId 查询单条数据
     *
     * @param libraryId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String libraryId) {
        Library library = this.querySimple(libraryId);
        if (library == null) {
            log.warn(libraryId + " 没有该图书馆信息");
            return ResMap.err("没有该图书馆信息");
        }

        List<LibraryRoom> libraryRooms = this.libraryRoomDao.queryByLibraryId(libraryId);
        library.setLibraryRooms(libraryRooms);


        return ResMap.ok(library);
    }

    public Library querySimple(String libraryId) {
        Library library;
        String libraryKey = String.format(RedisConfig.FormatKey.INFO.toString(), libraryId);
        String s = this.redisTemplate.opsForValue().get(libraryKey);

        if (StringUtils.isEmpty(s) || "".equals(s)) {
            library = this.libraryDao.queryById(libraryId);
            if (library == null) {
                return null;
            } else {
                // 储存到 redis
                redisTemplate.opsForValue().set(libraryKey, JSON.toJSONString(library));
            }
        } else {
            library = JSON.parseObject(s, Library.class);
        }
        return library;
    }


    /**
     * 通过 userId 获取图书馆列表
     *
     * @param userId userId
     * @return 实体
     */
    public Map<String, Object> queryByUserId(String userId) {
        UserSchool userSchool = this.userSchoolDao.queryByUserId(userId);
        if (userSchool == null) {
            return ResMap.err("没有绑定学校");
        }
        return this.queryBySchoolId(userSchool.getSchoolId());
    }


    /**
     * 通过schoolId 获取图书馆列表
     *
     * @param schoolId schoolId
     * @return map
     */
    public Map<String, Object> queryBySchoolId(String schoolId) {
        List<Library> libraries = this.libraryDao.queryBySchoolId(schoolId);

        if (libraries == null || libraries.size() == 0) {
            log.error(schoolId + " 没有该图书馆信息");
            return ResMap.err("没有该图书馆信息");
        }
        // 房间信息
        for (Library library : libraries) {
            List<LibraryRoom> libraryRooms = this.libraryRoomDao.queryByLibraryId(library.getLibraryId());
            library.setLibraryRooms(libraryRooms);
        }

        return ResMap.ok(libraries);
    }


    /**
     * 新增数据
     *
     * @param library 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(Library library, String userId) {
        // 判断是否为管理员
        UserSchool userSchool = this.userSchoolDao.queryByUserId(userId);
        if (userSchool == null) {
            return ResMap.err("没有我管理的学校");
        }
        if (!userSchool.getSchoolId().equals(library.getSchoolId())) {
            return ResMap.err("schoolId 输入错误");
        }
        if (!userSchool.getManagement()) {
            return ResMap.err("没有我管理的学校");
        }
        // 插入
        String libraryId = UUID.randomUUID().toString();
        library.setLibraryId(libraryId);
        if (this.libraryDao.insert(library) == 0) {
            log.warn("library 插入失败");
            return ResMap.err("library 插入失败");
        }
        // 清理缓存
        String libraryKey = String.format(RedisConfig.FormatKey.INFO.toString(), libraryId);
        this.redisTemplate.delete(libraryKey);

        // 返回
        library = this.querySimple(libraryId);
        return ResMap.ok(library);
    }

    /**
     * 修改数据
     *
     * @param library 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(Library library, String userId) {
        this.libraryDao.update(library);
        // 删除缓存
        String libraryKey = String.format(RedisConfig.FormatKey.INFO.toString(), library.getLibraryId());
        this.redisTemplate.delete(libraryKey);

        return this.queryById(library.getLibraryId());
    }

    /**
     * 通过主键删除数据
     *
     * @param libraryId 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String libraryId) {
        if (this.libraryDao.deleteById(libraryId) > 0) {
            return ResMap.ok();
        }
        return ResMap.err();
    }

}
