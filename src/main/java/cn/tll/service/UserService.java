package cn.tll.service;

import cn.tll.pojo.User;

/**
 * @author tll
 * @create 2020/7/24 14:29
 */
public interface UserService {
    User queryByName(String name);
}
