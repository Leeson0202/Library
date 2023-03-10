package cool.leeson.library.service.library.impl;

import cool.leeson.library.dao.UserSchoolDao;
import cool.leeson.library.entity.library.School;
import cool.leeson.library.dao.SchoolDao;
import cool.leeson.library.entity.user.UserSchool;
import cool.leeson.library.service.library.SchoolService;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (School)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 21:55:16
 */
@Service("schoolService")
@Slf4j
public class SchoolServiceImpl implements SchoolService {
    @Resource
    private SchoolDao schoolDao;
    @Resource
    private UserSchoolDao userSchoolDao;

    /**
     * 通过ID查询单条数据
     *
     * @param schoolId 主键
     * @return 实例对象
     */
    @Override
    public Map<String, Object> queryById(String schoolId) {
        School school = this.schoolDao.queryById(schoolId);
        if (school == null) {
            log.error(schoolId + " 学校不存在");
            return ResMap.err("学校不存在");

        }
        return ResMap.ok(school);
    }

    /**
     * 用户id查找学校
     *
     * @param userId 用户id
     * @return 实体
     */
    @Override
    public Map<String, Object> queryByUserId(String userId) {

        UserSchool userSchool = this.userSchoolDao.queryByUserId(userId);
        if (userSchool == null) {
            log.error(userId + " 没有绑定学校");
            return ResMap.err("没有绑定学校");
        }
        School school = this.schoolDao.queryById(userSchool.getSchoolId());
        if (school == null) {
            log.error(userSchool.getSchoolId() + " school不存在");
            return ResMap.err("系统错误");
        }
        return ResMap.ok(school);
    }

    /**
     * 分页查询
     *
     * @param school      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
    public boolean deleteById(String schoolId) {
        return this.schoolDao.deleteById(schoolId) > 0;
    }

}
