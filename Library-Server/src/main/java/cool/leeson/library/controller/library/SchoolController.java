package cool.leeson.library.controller.library;

import cool.leeson.library.entity.library.School;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.library.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * (School)表控制层
 *
 * @author Leeson0202
 * @since 2023-03-10 21:55:16
 */
@RestController
@RequestMapping("school")
@Slf4j
public class SchoolController {
    @Resource
    private HttpServletRequest request;
    /**
     * 服务对象
     */
    @Resource
    private SchoolService schoolService;

    /**
     * 获取学校信息 token
     */
    @GetMapping
    public Map<String, Object> queryByToken() throws MyException {
        return this.schoolService.queryByUserId((String) request.getAttribute("userId"));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param schoolId 主键
     * @return 单条数据
     */
    @GetMapping("id/{schoolId}")
    public Map<String, Object> queryById(@PathVariable("schoolId") String schoolId) {
        return this.schoolService.queryById(schoolId);
    }

    @GetMapping("simple")
    public Map<String, Object> simple(String schoolId) {
        return this.schoolService.querySimple(schoolId);
    }

    /**
     * 新增数据
     *
     * @param school 实体
     * @return 新增结果
     */
    @PostMapping
    public Map<String, Object> add(School school) throws MyException {
        return this.schoolService.insert(school, (String) request.getAttribute("userId"));
    }

    /**
     * 编辑数据
     *
     * @param school 实体
     * @return 编辑结果
     */
    @PutMapping
    public Map<String, Object> edit(School school) throws MyException {
        return this.schoolService.update(school, (String) request.getAttribute("userId"));
    }

    /**
     * 删除数据
     *
     * @param schoolId 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Map<String, Object> deleteById(String schoolId) throws MyException {
        return this.schoolService.deleteById(schoolId, (String) request.getAttribute("userId"));
    }



}

