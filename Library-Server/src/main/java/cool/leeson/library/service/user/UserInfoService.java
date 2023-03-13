package cool.leeson.library.service.user;

import cool.leeson.library.dao.UserRecordDao;
import cool.leeson.library.entity.user.UserInfo;
import cool.leeson.library.dao.UserInfoDao;
import cool.leeson.library.entity.user.UserRecord;
import cool.leeson.library.util.CheckObjectIsNullUtils;
import cool.leeson.library.util.ResMap;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (UserInfo)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-02-27 23:53:28
 */
@Service("userInfoService")
public class UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserRecordDao userRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    public UserInfo queryById(String userId) {
        return this.userInfoDao.queryById(userId);
    }


    public UserInfo query(String userId) {
        UserInfo userInfo = this.userInfoDao.queryById(userId);
        UserRecord userRecord = userRecordDao.queryByUserId(userId);
        userInfo.setUserRecord(userRecord);

        return userInfo;
    }

    /**
     * 分页查询
     *
     * @param userInfo    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
  
    public Page<UserInfo> queryByPage(UserInfo userInfo, PageRequest pageRequest) {
        long total = this.userInfoDao.count(userInfo);
        return new PageImpl<>(this.userInfoDao.queryAllByLimit(userInfo, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param userInfo 实例对象
     * @return 实例对象
     */
  
    public UserInfo insert(UserInfo userInfo) {
        this.userInfoDao.insert(userInfo);
        return userInfo;
    }

    /**
     * 修改数据
     *
     * @param userInfo 实例对象
     * @return 实例对象
     */
  
    public UserInfo update(UserInfo userInfo) {
        this.userInfoDao.update(userInfo);
        userInfo = this.queryById(userInfo.getUserId());
        UserRecord userRecord = this.userRecordDao.queryByUserId(userInfo.getUserId());
        userInfo.setUserRecord(userRecord);
        return userInfo;
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
  
    public boolean deleteById(Integer userId) {
        return this.userInfoDao.deleteById(userId) > 0;
    }
}
