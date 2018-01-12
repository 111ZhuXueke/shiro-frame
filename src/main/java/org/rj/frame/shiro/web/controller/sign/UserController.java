package org.rj.frame.shiro.web.controller.sign;

import com.rui.web.common.enums.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
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
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author : zhuxueke
 * @since : 2018-01-11 16:10
 **/
@Controller
@Scope("prototype")
@RequestMapping("/")
public class UserController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/signIn",method = RequestMethod.GET)
    ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("signIn/login");
        return modelAndView;
    }

    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    @ResponseBody
    String login(UserQuery query,Model model){
        try{
            UserDomain userDomain = userService.getOne(query);
            if(userDomain != null){
                model.addAttribute("user",userDomain);
                UsernamePasswordToken token = new UsernamePasswordToken(userDomain.getUserName(), userDomain.getPassword());
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
                logger.info("==========登录成功============");
            }else{
                return errorObjectStr("用户名或密码错误!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return successObjectStr("登录中......");
    }

    @RequestMapping("/index")
    ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/system/index");
        return modelAndView;
    }
}
