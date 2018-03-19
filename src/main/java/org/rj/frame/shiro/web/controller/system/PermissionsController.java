package org.rj.frame.shiro.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.rui.web.common.enums.PermissionType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rj.frame.shiro.service.domain.admin.PermissionDomain;
import org.rj.frame.shiro.service.query.PermissionQuery;
import org.rj.frame.shiro.service.service.IPermissionAssignService;
import org.rj.frame.shiro.service.service.IPermissionService;
import org.rj.frame.shiro.web.util.ShiroPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *  权限资源管理
 * @author : zhuxueke
 * @since : 2018-03-12 17:17
 **/
@ShiroPermissions(name = "系统设置",type = PermissionType.CLICK, moduleLabel = "system")
@RequiresPermissions("system:permissions:manager")
@Scope("prototype")
@Controller
@RequestMapping(value = "/permission")
public class PermissionsController {

    @Autowired
    IPermissionService permissionService;

    @Autowired
    IPermissionAssignService permissionAssignService;

    /**
     * 权限列表管理
     * @author : zhuxueke
     * @since : 2018/3/19 14:17
     */
    @ShiroPermissions(name = "权限列表",type = PermissionType.MENU, moduleLabel = "system")
    @RequiresPermissions(value = "system:permissions:index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView model = new ModelAndView();
        model.setViewName("system/list");
        return model;
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions(value = "system:permissions:index")
    @ResponseBody
    public String list(PermissionQuery query){
        List<PermissionDomain> list = permissionService.getList(query);
        return JSONObject.toJSONString(list);
    }
}
