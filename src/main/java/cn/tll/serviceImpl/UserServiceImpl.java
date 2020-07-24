package cn.tll.serviceImpl;

import cn.tll.mapper.UserMapper;
import cn.tll.pojo.User;
import cn.tll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tll
 * @create 2020/7/24 14:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }
}
