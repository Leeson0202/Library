package cool.leeson.library.service.library;

import cool.leeson.library.dao.SchoolNotificationDao;
import cool.leeson.library.entity.library.SchoolNotification;
import cool.leeson.library.service.user.UserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (SchoolNotification)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-04-10 00:28:20
 */
@Service("schoolNotificationService")
public class SchoolNotificationService {
    @Resource
    private SchoolNotificationDao schoolNotificationDao;

    @Resource
    private UserInfoService userInfoService;

    /**
     * 通过ID查询单条数据
     *
     * @param notificationId 主键
     * @return 实例对象
     */
    public SchoolNotification queryById(String notificationId) {
        SchoolNotification notification = this.schoolNotificationDao.queryById(notificationId);
        notification.setNickName(userInfoService.queryById(notification.getUserId()).getNickName());

        return notification;
    }

    /**
     * 分页查询
     *
     * @param schoolNotification 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    public Page<SchoolNotification> queryByPage(SchoolNotification schoolNotification, PageRequest pageRequest) {
        long total = this.schoolNotificationDao.count(schoolNotification);
        List<SchoolNotification> schoolNotifications = this.schoolNotificationDao.queryAllByLimit(schoolNotification, pageRequest);
        for (SchoolNotification notification : schoolNotifications) {
            notification.setNickName(userInfoService.queryById(notification.getUserId()).getNickName());
        }
        return new PageImpl<>(schoolNotifications, pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param schoolNotification 实例对象
     * @return 实例对象
     */
    public SchoolNotification insert(SchoolNotification schoolNotification) {
        schoolNotification.setNotificationId(UUID.randomUUID().toString());
        schoolNotification.setDate(new Date());
        schoolNotification.setView(0);
        this.schoolNotificationDao.insert(schoolNotification);
        return schoolNotification;
    }

    /**
     * 修改数据
     *
     * @param schoolNotification 实例对象
     * @return 实例对象
     */
    public SchoolNotification update(SchoolNotification schoolNotification) {
        schoolNotification.setDate(new Date());
        this.schoolNotificationDao.update(schoolNotification);
        return this.queryById(schoolNotification.getNotificationId());
    }

    /**
     * 通过主键删除数据
     *
     * @param notificationId 主键
     * @return 是否成功
     */
    public boolean deleteById(String notificationId) {
        return this.schoolNotificationDao.deleteById(notificationId) > 0;
    }
}
