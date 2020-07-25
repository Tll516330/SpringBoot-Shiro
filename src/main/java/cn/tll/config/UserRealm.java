package cn.tll.config;

import cn.tll.pojo.User;
import cn.tll.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tll
 * @create 2020/7/24 10:08
 * 自定义的UserRealm  继承AuthorizingRealm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权操作doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证操作+doGetAuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userService.queryByName(userToken.getUsername());
        if (user == null){
            return null;
            //抛出UnknownAccountException异常
        }

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",user);

        //密码认证Shiro做  防止密码泄露
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
