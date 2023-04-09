package cool.leeson.library.service.library;

import cool.leeson.library.dao.SchoolRuleDao;
import cool.leeson.library.entity.library.SchoolRule;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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
    public Map<String, Object> queryById(String schoolId) {
        return ResMap.ok(this.schoolRuleDao.queryById(schoolId));
    }


    /**
     * 新增数据
     *
     * @param schoolRule 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(SchoolRule schoolRule) {
        this.schoolRuleDao.insert(schoolRule);
        return ResMap.ok(schoolRule);
    }

    /**
     * 修改数据
     *
     * @param schoolRule 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(SchoolRule schoolRule) {
        this.schoolRuleDao.update(schoolRule);
        return this.queryById(schoolRule.getSchoolId());
    }

    /**
     * 通过主键删除数据
     *
     * @param schoolId 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String schoolId) throws MyException {
        if (this.schoolRuleDao.deleteById(schoolId) == 0) {
            throw new MyException("删除失败");
        }
        return ResMap.ok("修改成功");
    }
}
