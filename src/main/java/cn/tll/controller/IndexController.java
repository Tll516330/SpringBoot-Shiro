package cn.tll.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tll
 * @create 2020/7/24 9:55
 */
@Controller
public class IndexController {

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("msg","Hello,Shiro");
        return "index";
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    /**
     * 更新用户
     * @return
     */
    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    /**
     * 登录界面
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/toLogin")
    public String Login(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //执行登录
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            //用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }

    @RequestMapping("/NoAuthorization")
    @ResponseBody
    public String NoAuthorization(){
        return "未经授权,不允许访问此页面";
    }


}
