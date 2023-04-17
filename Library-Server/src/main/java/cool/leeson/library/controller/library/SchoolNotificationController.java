package cool.leeson.library.controller.library;

import com.aliyuncs.utils.StringUtils;
import cool.leeson.library.entity.library.SchoolNotification;
import cool.leeson.library.service.library.SchoolNotificationService;
import cool.leeson.library.util.ResMap;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * (SchoolNotification)表控制层
 *
 * @author Leeson0202
 * @since 2023-04-10 00:28:20
 */
@RestController
@RequestMapping("notification")
@Validated
public class SchoolNotificationController {
    /**
     * 服务对象
     */
    @Resource
    private SchoolNotificationService schoolNotificationService;

    /**
     * 分页查询
     *
     * @param schoolNotification 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public Object queryByPage(
            SchoolNotification schoolNotification,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(schoolNotification.getSchoolId())) return ResMap.err("schoolId为空");
        return ResMap.ok(this.schoolNotificationService.queryByPage(schoolNotification, PageRequest.of(page, size, Sort.by("date").descending())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public Object queryById(@PathVariable("id") String id) {
        return ResMap.ok(this.schoolNotificationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param schoolNotification 实体
     * @return 新增结果
     */
    @PostMapping
    public Object add(@RequestBody @Validated SchoolNotification schoolNotification) {
        SchoolNotification notification = this.schoolNotificationService.insert(schoolNotification);
        return ResMap.ok(notification);
    }

    /**
     * 编辑数据
     *
     * @param schoolNotification 实体
     * @return 编辑结果
     */
    @PutMapping
    public Object edit(@Validated SchoolNotification schoolNotification) {
        return ResMap.ok(this.schoolNotificationService.update(schoolNotification));
    }

    /**
     * 删除数据
     *
     * @param notificationId 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Object deleteById(@NotBlank(message = "notificationId 不能为空") String notificationId) {
        System.out.println("hello1");
        return ResMap.ok(this.schoolNotificationService.deleteById(notificationId));
    }

}

