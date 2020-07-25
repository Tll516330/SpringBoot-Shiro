package cn.tll.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tll
 * @create 2020/7/24 10:12
 */
@Configuration
public class ShiroConfig {

    /**
     * ShiroFilterFactoryBean 3
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterDactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        /**
         * 添加shiro内置过滤器
         * anon:无需认证就可以访问
         * authc:必须认证了才能访问
         * user: 必须拥有自我认证功能才能访问
         * perms: 拥有某个权限的资源才能访问
         * role: 拥有某个角色才能访问
         */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权，正常的情况下，没有授权会跳转到未授权页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        filterMap.put("/user/*","authc");

        //没有权限,跳转到没有权限页面
        bean.setUnauthorizedUrl("/NoAuthorization");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        bean.setLoginUrl("/login");
        return bean;
    }

    /**
     * 创建DefaultWebSecurityManager  :2
     * @param userRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager manager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建Realm对象，需要自定义类:1
     * @return
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 整合ShiroDialect :用来整合Shiro thymeleaf
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
