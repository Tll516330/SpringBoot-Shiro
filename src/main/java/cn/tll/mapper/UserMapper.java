package cn.tll.mapper;

import cn.tll.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * @author tll
 * @create 2020/7/24 14:15
 */
@Repository
public interface UserMapper {

    /**
     * 根据用户名查询User
     * @param name
     * @return
     */
    User queryByName(String name);
}
