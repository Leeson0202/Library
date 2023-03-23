package cool.leeson.library.service.user;

import cool.leeson.library.dao.UserOnlineDao;
import cool.leeson.library.entity.user.UserOnline;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.util.ResMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * (UserOnline)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-24 01:05:34
 */
@Service("userOnlineService")
public class UserOnlineService {
    @Resource
    private UserOnlineDao userOnlineDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String userId) throws MyException {
        UserOnline userOnline = this.userOnlineDao.queryById(userId);
        if (userOnline == null) {
            userOnline = new UserOnline(userId, 0, new Date());
            int i = this.userOnlineDao.insert(userOnline);
            if (i<1) throw new MyException(MyException.STATUS.err);
        }
        return ResMap.ok(userOnline);
    }


    /**
     * 分页查询
     *
     * @param userOnline  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    public Page<UserOnline> queryByPage(UserOnline userOnline, PageRequest pageRequest) {
        long total = this.userOnlineDao.count(userOnline);
        return new PageImpl<>(this.userOnlineDao.queryAllByLimit(userOnline, pageRequest), pageRequest, total);
    }


    /**
     * 修改数据
     *
     * @param userOnline 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(UserOnline userOnline) throws MyException {
        this.userOnlineDao.update(userOnline);
        return this.queryById(userOnline.getUserId());
    }
}
