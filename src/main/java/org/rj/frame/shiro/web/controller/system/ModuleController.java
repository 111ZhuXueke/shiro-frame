package org.rj.frame.shiro.web.controller.system;

import org.rj.frame.shiro.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
