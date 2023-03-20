package cool.leeson.library.service.library;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.StringUtils;
import cool.leeson.library.config.RedisConfig;
import cool.leeson.library.dao.LibraryDao;
import cool.leeson.library.dao.SchoolDao;
import cool.leeson.library.dao.SchoolRuleDao;
import cool.leeson.library.dao.UserSchoolDao;
import cool.leeson.library.entity.library.Library;
import cool.leeson.library.entity.library.School;
import cool.leeson.library.entity.library.SchoolRule;
import cool.leeson.library.entity.user.UserSchool;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (School)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-10 21:55:16
 */
@Service("schoolService")
@Slf4j
public class SchoolService {
    @Resource
    private SchoolDao schoolDao;
    @Resource
    private UserSchoolDao userSchoolDao;
    @Resource
    private LibraryDao libraryDao;
    @Resource
    private SchoolRuleDao schoolRuleDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param schoolId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String schoolId) {
        School school = this.querySimple(schoolId);

        if (school == null) {
            log.error(schoolId + " 学校不存在");
            return ResMap.err("学校不存在");
        }
        // 查询图书馆 及 预约规则
        List<Library> libraries = this.libraryDao.queryBySchoolId(schoolId);
        SchoolRule schoolRule = this.schoolRuleDao.queryById(schoolId);
        school.setLibraries(libraries);
        school.setSchoolRule(schoolRule);
        return ResMap.ok(school);
    }

    public School querySimple(String schoolId) {
        School school;
        String schoolKey = String.format(RedisConfig.FormatKey.INFO.toString(), schoolId);
        String s = this.redisTemplate.opsForValue().get(schoolKey);

        if (StringUtils.isEmpty(s) || "".equals(s)) {
            school = this.schoolDao.queryById(schoolId);
            if (school == null) {
                return null;
            } else {
                // 储存到 redis
                redisTemplate.opsForValue().set(schoolKey, JSON.toJSONString(school));
            }
        } else {
            school = JSON.parseObject(s, School.class);
        }
        return school;
    }

    /**
     * 用户id查找学校
     *
     * @param userId 用户id
     * @return 实体
     */
    public Map<String, Object> queryByUserId(String userId) {
        UserSchool userSchool = this.userSchoolDao.queryByUserId(userId);
        if (userSchool == null) {
            log.warn(userId + " 没有绑定学校");
            return ResMap.ok();
        }
        return this.queryById(userSchool.getSchoolId());
    }

    /**
     * 分页查询
     *
     * @param school      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    public Page<School> queryByPage(School school, PageRequest pageRequest) {
        long total = this.schoolDao.count(school);
        return new PageImpl<>(this.schoolDao.queryAllByLimit(school, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param school 实例对象
     * @return 实例对象
     */
    public School insert(School school) {
        this.schoolDao.insert(school);
        return school;
    }

    /**
     * 修改数据
     *
     * @param school 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(School school) {
        this.schoolDao.update(school);
        return ResMap.ok(this.queryById(school.getSchoolId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param schoolId 主键
     * @return 是否成功
     */

    public boolean deleteById(String schoolId) {
        return this.schoolDao.deleteById(schoolId) > 0;
    }

}
