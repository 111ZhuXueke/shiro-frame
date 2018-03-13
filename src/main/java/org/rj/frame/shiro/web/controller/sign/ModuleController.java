package org.rj.frame.shiro.web.controller.sign;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.rj.frame.shiro.service.model.UserModel;
import org.rj.frame.shiro.service.service.IUserService;
import org.rj.frame.shiro.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 模块控制层
 * @author : zhuxueke
 * @since : 2018/3/13 10:35
 */
@Controller
@Scope("prototype")
@RequestMapping("/")
public class ModuleController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);


}
