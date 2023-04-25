package cool.leeson.lib.service;

import cool.leeson.lib.dao.UserDao;
import cool.leeson.lib.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl {
    @Resource
    private UserDao userDao;

    public String login(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone_num", id);
        List<User> users = userDao.selectByMap(map);
        System.out.println(users);
        return id + users.size();
    }
}
