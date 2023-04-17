package cool.leeson.library.controller.library;

import cool.leeson.library.entity.library.SchoolRule;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.library.SchoolRuleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (SchoolRule)表控制层
 *
 * @author Leeson0202
 * @since 2023-04-09 23:37:17
 */
@RestController
@RequestMapping("rule")
public class SchoolRuleController {
    /**
     * 服务对象
     */
    @Resource
    private SchoolRuleService schoolRuleService;


    /**
     * 通过主键查询单条数据
     *
     * @param schoolId 主键
     * @return 单条数据
     */
    @GetMapping("id/{schoolId}")
    public Object queryById(@PathVariable("schoolId") String schoolId) {
        return this.schoolRuleService.queryById(schoolId);
    }

    /**
     * 新增数据
     *
     * @param schoolRule 实体
     * @return 新增结果
     */
    @PostMapping
    public Map<String, Object> add(@RequestBody @Validated SchoolRule schoolRule) {
        return this.schoolRuleService.insert(schoolRule);
    }

    /**
     * 编辑数据
     *
     * @param schoolRule 实体
     * @return 编辑结果
     */
    @PutMapping
    public Map<String, Object> edit(SchoolRule schoolRule) {
        return this.schoolRuleService.update(schoolRule);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Map<String, Object> deleteById(String id) throws MyException {
        return this.schoolRuleService.deleteById(id);
    }

}

