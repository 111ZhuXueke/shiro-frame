package org.rj.frame.shiro.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.rui.web.common.enums.PermissionType;
import com.rui.web.common.utils.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rj.frame.shiro.service.domain.admin.PermissionDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.rj.frame.shiro.service.query.PermissionQuery;
import org.rj.frame.shiro.service.query.UserQuery;
import org.rj.frame.shiro.service.service.IPermissionAssignService;
import org.rj.frame.shiro.service.service.IPermissionService;
import org.rj.frame.shiro.service.service.IUserService;
import org.rj.frame.shiro.web.controller.base.BaseController;
import org.rj.frame.shiro.web.util.ShiroPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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
public class PermissionsController extends BaseController{

    @Autowired
    IUserService userService;

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
        if(org.apache.commons.lang3.StringUtils.isEmpty(query.getName())){
            query.setName(null);
        }
        List<PermissionDomain> list = permissionService.getList(query);
        return JSONObject.toJSONString(list);
    }

    @ShiroPermissions(name = "新增权限",type = PermissionType.BUTTON, moduleLabel = "system",parentPermissions = "system:permissions:index")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @RequiresPermissions(value = "system:permissions:add")
    public ModelAndView add(){
        ModelAndView model = new ModelAndView();
        model.setViewName("system/add");
        return model;
    }

    @ShiroPermissions(name = "新增权限",type = PermissionType.BUTTON, moduleLabel = "system",parentPermissions = "system:permissions:index")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @RequiresPermissions(value = "system:permissions:add")
    @ResponseBody
    public String add(PermissionDomain domain){
        try {
            PermissionQuery query = new PermissionQuery();
            query.setName(domain.getName());
            query.setType(domain.getType());
            query.setUrl(domain.getUrl());
            PermissionDomain domain1 = permissionService.getFirst(query);
            if(domain1 != null){
                return errorObjectStr("资源重复！");
            }
            domain.setId(permissionService.getMaxId());
            domain.setUpdateTime(new Date());
            domain.setCreateTime(new Date());
            permissionService.create(domain);
            return successObjectStr("添加成功!");
        }catch (Exception e){
            return successObjectStr("添加失败!");
        }
    }

    @ShiroPermissions(name = "修改权限",type = PermissionType.BUTTON, moduleLabel = "system",parentPermissions = "system:permissions:index")
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @RequiresPermissions(value = "system:permissions:update")
    public ModelAndView update(PermissionQuery query){
        ModelAndView model = new ModelAndView();
        model.setViewName("system/update");
        PermissionDomain domain = permissionService.getFirst(query);
        model.addObject("domain",domain);
        return model;
    }

    @ShiroPermissions(name = "修改权限",type = PermissionType.BUTTON, moduleLabel = "system",parentPermissions = "system:permissions:index")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @RequiresPermissions(value = "system:permissions:update")
    @ResponseBody
    public String update(PermissionDomain domain){
        try{
            permissionService.update(domain);
            return successObjectStr("修改成功！");
        }catch (Exception e){
            return errorObjectStr("修改失败！");
        }
    }
    @ShiroPermissions(name = "分配权限",type = PermissionType.MENU, moduleLabel = "system",parentPermissions = "system:permissions:system")
    @RequestMapping(value = "/distribution",method = RequestMethod.GET)
    @RequiresPermissions(value = "system:permissions:distribution")
    public ModelAndView permissions(){
        ModelAndView model = new ModelAndView();
        model.setViewName("system/distribution");
        return model;
    }

    @ShiroPermissions(name = "分配权限",type = PermissionType.MENU, moduleLabel = "system",parentPermissions = "system:permissions:system")
    @RequestMapping(value = "/distribution",method = RequestMethod.POST)
    @RequiresPermissions(value = "system:permissions:distribution")
    @ResponseBody
    public String permissions(UserQuery query){
        List<UserDomain> domains = userService.getList(query);
        return JSONObject.toJSONString(domains);
    }

    /**
     * 获取当前权限类型的所有父权限类型  button -> all menu
     * @author : zhuxueke
     * @since : 2018/3/21 14:59
     */
    @RequestMapping(value = "/getParentType")
    @ResponseBody
    public String getParentType(String lable){
        PermissionQuery query = new PermissionQuery();
        query.setAvailable(0);
        if (PermissionType.BUTTON.getType().equals(lable)){
            query.setType(PermissionType.MENU.getType());
        }else if(PermissionType.MENU.getType().equals(lable)){
            query.setType(PermissionType.CLICK.getType());
        }
        List<PermissionDomain> domains = permissionService.getList(query);
        return JSONObject.toJSONString(domains);
    }

}
