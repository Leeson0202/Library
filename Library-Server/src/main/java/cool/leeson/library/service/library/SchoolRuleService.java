package cool.leeson.library.service.library;

import cool.leeson.library.dao.SchoolRuleDao;
import cool.leeson.library.entity.library.SchoolRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (SchoolRule)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-11 20:18:35
 */
@Service("schoolRuleService")
@Slf4j
public class SchoolRuleService {
    @Resource
    private SchoolRuleDao schoolRuleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param schoolId 主键
     * @return 实例对象
     */
    public SchoolRule queryById(String schoolId) {
        return this.schoolRuleDao.queryById(schoolId);
    }

    /**
     * 分页查询
     *
     * @param schoolRule  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    public Page<SchoolRule> queryByPage(SchoolRule schoolRule, PageRequest pageRequest) {
        long total = this.schoolRuleDao.count(schoolRule);
        return new PageImpl<>(this.schoolRuleDao.queryAllByLimit(schoolRule, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param schoolRule 实例对象
     * @return 实例对象
     */
    public SchoolRule insert(SchoolRule schoolRule) {
        this.schoolRuleDao.insert(schoolRule);
        return schoolRule;
    }

    /**
     * 修改数据
     *
     * @param schoolRule 实例对象
     * @return 实例对象
     */
    public SchoolRule update(SchoolRule schoolRule) {
        this.schoolRuleDao.update(schoolRule);
        return this.queryById(schoolRule.getSchoolId());
    }

    /**
     * 通过主键删除数据
     *
     * @param schoolId 主键
     * @return 是否成功
     */
    public boolean deleteById(String schoolId) {
        return this.schoolRuleDao.deleteById(schoolId) > 0;
    }
}
