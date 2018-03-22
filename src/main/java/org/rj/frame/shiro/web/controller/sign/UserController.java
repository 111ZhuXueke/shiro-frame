package org.rj.frame.shiro.web.controller.sign;

import com.rui.web.common.enums.Constant;
import com.rui.web.common.security.user.AdminContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.rj.frame.shiro.service.model.UserModel;
import org.rj.frame.shiro.service.query.UserQuery;
import org.rj.frame.shiro.service.service.IUserService;
import org.rj.frame.shiro.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户
 * @author : zhuxueke
 * @since : 2018-01-11 16:10
 **/
@Controller
@Scope("prototype")
public class UserController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AdminContext adminContext;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/signIn",method = RequestMethod.GET)
    ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("signIn/login");
        return modelAndView;
    }

    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    @ResponseBody
    String login(HttpSession session,UserModel userModel, Model model){
        String info = loginUser(userModel);
        if (!"SUCC".equals(info)) {
            return errorObjectStr("用户不存在或密码错误！");
        }else{
            session.setAttribute("status",1);
            return successObjectStr("登陆成功！");//返回的页面
        }
    }

    private String loginUser(UserModel user) {
        if (isRelogin(user)) return "SUCC"; // 如果已经登陆，无需重新登录

        return shiroLogin(user); // 调用shiro的登陆验证
    }
    private String shiroLogin(UserModel user) {
        // 组装token，包括客户公司名称、简称、客户编号、用户名称；密码
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword().toCharArray(), null);
        token.setRememberMe(true);
        // shiro登陆验证
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException ex) {
            return "用户不存在或者密码错误！";
        } catch (IncorrectCredentialsException ex) {
            return "用户不存在或者密码错误！";
        } catch (AuthenticationException ex) {
            return ex.getMessage(); // 自定义报错信息
        } catch (Exception ex) {
            ex.printStackTrace();
            return "内部错误，请重试！";
        }
        return "SUCC";
    }

    private boolean isRelogin(UserModel user) {
        Subject us = SecurityUtils.getSubject();
        if (us.isAuthenticated()) {
            return true; // 参数未改变，无需重新登录，默认为已经登录成功
        }
        return false; // 需要重新登陆
    }

    /**
     * 进入主页面
     * @author : zhuxueke
     * @since : 2018/3/16 13:15
     */
    @RequestMapping(value = "/index")
    public ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signIn/index");
        return modelAndView;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public String logout(){
        Subject subject= SecurityUtils.getSubject();
        try {
            subject.logout();
            adminContext = null;
        }catch (Exception e){
            return errorObjectStr("退出失败!");
        }
        return successObjectStr("成功退出！");
    }
}
