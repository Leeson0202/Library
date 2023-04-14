package cool.leeson.library.service.user;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.dao.CquptInfoDao;
import cool.leeson.library.dao.UserDao;
import cool.leeson.library.dao.UserInfoDao;
import cool.leeson.library.dao.UserSchoolDao;
import cool.leeson.library.entity.user.CquptInfo;
import cool.leeson.library.entity.user.User;
import cool.leeson.library.entity.user.UserInfo;
import cool.leeson.library.entity.user.UserSchool;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * (User)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-02-26 15:42:15
 */
@Service("userService")
@Slf4j
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private CquptInfoDao cquptInfoDao;
    @Resource
    private UserSchoolDao userSchoolDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private HttpServletRequest request;
    @Resource
    private JwtConfig jwtConfig;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String userId) {
        User user = this.userDao.queryById(userId);
        if (user == null) {
            log.error(userId + " 用户不存在");
            return ResMap.err("用户不存在");
        }
        user.setNickName(this.userInfoDao.queryById(userId).getNickName());
        return ResMap.ok(user);
    }

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public User insert(User user) throws MyException {
        String userId = jwtConfig.getUsernameFromToken(
                request.getHeader("token"));
        UserSchool userSchool = this.userSchoolDao.queryByUserId(userId);
        userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        //  userSchool
        UserSchool userSchool1 = new UserSchool();
        userSchool1.setId(UUID.randomUUID().toString());
        userSchool1.setUserId(userId);
        userSchool1.setSchoolId(userSchool.getSchoolId());
        userSchool1.setManagement(false);
        //userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setNickName(user.getNickName());
        userInfoDao.insert(userInfo);
        userSchoolDao.insert(userSchool1);
        this.userDao.insert(user);


        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    public boolean deleteById(String userId) {
        // 删除记录
        // user credit
        // learned
        // online
        // record

        // 删除userSchool
        UserSchool userSchool = this.userSchoolDao.queryByUserId(userId);
        userSchoolDao.deleteById(userSchool.getId());
        // 删除userInfo
        // 删除suer
        //


        return this.userDao.deleteById(userId) > 0;
    }


    /**
     * 通过token获取 学校身份信息
     *
     * @return 实体
     */
    public Map<String, Object> queryCquptInfoByToken(String userId) throws MyException {
        CquptInfo cquptInfo = this.cquptInfoDao.queryById(userId);
        if (cquptInfo == null) {
            throw new MyException("请绑定学校");
        }
        return ResMap.ok(cquptInfo);
    }


    /**
     * 获取学校的所有用户
     */
    public Object queryAll(String schoolId) {
        UserSchool userSchool = new UserSchool();
        userSchool.setSchoolId(schoolId);
        List<UserSchool> userSchools = userSchoolDao.queryAllByLimit(userSchool, PageRequest.of(0, Integer.MAX_VALUE));
        List<User> users = new ArrayList<>();
        for (UserSchool userSchool1 : userSchools) {
            if (userSchool1.getManagement()) continue;
            String userId = userSchool1.getUserId();
            User user = (User) this.queryById(userId).get("data");
            users.add(user);
        }
        return ResMap.ok(users);
    }
}
