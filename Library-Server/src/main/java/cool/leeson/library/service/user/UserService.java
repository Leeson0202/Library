package cool.leeson.library.service.user;

import cool.leeson.library.dao.CquptInfoDao;
import cool.leeson.library.dao.UserDao;
import cool.leeson.library.entity.user.CquptInfo;
import cool.leeson.library.entity.user.User;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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
    public User insert(User user) {
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
    public boolean deleteById(Integer userId) {
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
}
