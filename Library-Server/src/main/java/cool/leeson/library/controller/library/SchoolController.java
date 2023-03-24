package cool.leeson.library.controller.library;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.library.School;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.library.SchoolService;
import org.springframework.http.ResponseEntity;
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
public class SchoolController {
    @Resource
    private HttpServletRequest request;
    /**
     * 服务对象
     */
    @Resource
    private SchoolService schoolService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping
    public Map<String, Object> queryByToken() throws MyException {
        // 解析token获取userId
        String userId = new JwtConfig().getUsernameFromToken(request.getHeader("token"));
        return this.schoolService.queryByUserId(userId);
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
        return this.schoolService.schoolSimple(schoolId);
    }

    /**
     * 新增数据
     *
     * @param school 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<School> add(School school) {
        return ResponseEntity.ok(this.schoolService.insert(school));
    }

    /**
     * 编辑数据
     *
     * @param school 实体
     * @return 编辑结果
     */
    @PutMapping
    public Map<String, Object> edit(School school) {
        return this.schoolService.update(school);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.schoolService.deleteById(id));
    }

}

