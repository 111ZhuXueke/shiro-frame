package org.rj.frame.shiro.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.rui.web.common.enums.PermissionType;
import com.rui.web.common.utils.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rj.frame.shiro.service.domain.admin.PermissionAssignDomain;
import org.rj.frame.shiro.service.domain.admin.PermissionDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.rj.frame.shiro.service.query.PermissionQuery;
import org.rj.frame.shiro.service.query.UserQuery;
import org.rj.frame.shiro.service.service.*;
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

import java.util.*;
import java.util.stream.Collectors;

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
    IRoleService roleService;

    @Autowired
    IRoleAssignService roleAssignService;

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
            domain.setUpdateTime(new Date());
            domain.setCreateTime(new Date());
            permissionService.create(domain);
            // 系统设置默认给管理员添加权限
            if(domain.getModuleId() == 1){
                PermissionAssignDomain domain2 = new PermissionAssignDomain();
                domain2.setCreateTime(new Date());
                domain2.setPermissionId(domain.getId());
                domain2.setRoleId(1L);
                permissionAssignService.create(domain2);
            }
            return successObjectStr("添加成功!");
        }catch (Exception e){
            e.printStackTrace();
            return errorObjectStr("添加失败!");
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
    @ShiroPermissions(name = "分配权限管理",type = PermissionType.MENU, moduleLabel = "system",parentPermissions = "system:permissions:system")
    @RequestMapping(value = "/distribution",method = RequestMethod.GET)
    @RequiresPermissions(value = "system:permissions:distribution")
    public ModelAndView permissions(){
        ModelAndView model = new ModelAndView();
        model.setViewName("system/distribution");
        return model;
    }

    @ShiroPermissions(name = "分配权限管理",type = PermissionType.MENU, moduleLabel = "system",parentPermissions = "system:permissions:system")
    @RequestMapping(value = "/distribution",method = RequestMethod.POST)
    @RequiresPermissions(value = "system:permissions:distribution")
    @ResponseBody
    public String permissions(UserQuery query){
        List<UserDomain> domains = userService.getList(query);
        return JSONObject.toJSONString(domains);
    }

    @ShiroPermissions(name = "分配权限",type = PermissionType.BUTTON, moduleLabel = "system",parentPermissions = "system:permissions:distribution")
    @RequestMapping(value = "/disPermission",method = RequestMethod.GET)
    @RequiresPermissions(value = "system:permissions:disPermission")
    public ModelAndView disPermission(Long userId){
        ModelAndView model = new ModelAndView();
        model.setViewName("system/disAdd");
        // 获取所有数据
        List<PermissionDomain> allPermissions = permissionService.getList(new PermissionQuery());
        List<PermissionDomain> click = allPermissions.stream().filter((PermissionDomain p) -> p.getType().equals(PermissionType.CLICK.getType())).collect(Collectors.toList());
        List<PermissionDomain> menu = allPermissions.stream().filter((PermissionDomain p) -> p.getType().equals(PermissionType.MENU.getType())).collect(Collectors.toList());
        List<PermissionDomain> button = allPermissions.stream().filter((PermissionDomain p) -> p.getType().equals(PermissionType.BUTTON.getType())).collect(Collectors.toList());
        model.addObject("click",click);
        model.addObject("menu",menu);
        model.addObject("button",button);

        /* 获取用户所有的权限字符串 */
        // 绑定角色对象
        UserQuery userQuery = new UserQuery();
        userQuery.setId(userId);
        Set<Long> roleIds = roleAssignService.getRolesId(userService.getOne(userQuery));
        // 权限对象
        Set<Long> permissionIds = permissionAssignService.getPermissionAssign(roleIds);
        Set<PermissionDomain> permissionDomains = permissionService.getPermissionsById(permissionIds);
        Set<String> permissions = new HashSet<String>();
        for (PermissionDomain item:permissionDomains) {
            permissions.add(item.getPermission());
        }
        List<String> userPermissions = new ArrayList<>(permissions);
        model.addObject("userId",userId);
        model.addObject("userPermissions",userPermissions);
        return model;
    }

    @ShiroPermissions(name = "分配权限",type = PermissionType.BUTTON, moduleLabel = "system",parentPermissions = "system:permissions:distribution")
    @RequestMapping(value = "/disPermission",method = RequestMethod.POST)
    @RequiresPermissions(value = "system:permissions:disPermission")
    @ResponseBody
    public String disPermission(String permissionIds,Long userId){
        try {
            String[] arrayList = permissionIds.split(",");
            // 获取所有用户可用权限对象
            UserQuery userQuery = new UserQuery();
            userQuery.setId(userId);
            Set<Long> roleIds = roleAssignService.getRolesId(userService.getOne(userQuery));
            // 权限对象
            Set<Long> permissionId = permissionAssignService.getPermissionAssign(roleIds);
            // 转换set对象
            Set<Long> upPers = new HashSet<>();
            for (String item: arrayList) {
                upPers.add(Long.valueOf(item));
            }
            // 获取到 存在和不存在的资源id
            Collection exists = new ArrayList(upPers);
            Collection notExists = new ArrayList(permissionId);
            upPers.removeAll(permissionId);
            permissionId.removeAll(upPers);
            // 添加新的权限
            for (Object item: exists) {
                PermissionAssignDomain assignDomain = new PermissionAssignDomain();
                assignDomain.setRoleId(userId);
                assignDomain.setCreateTime(new Date());
                assignDomain.setPermissionId((Long)item);
                permissionAssignService.create(assignDomain);
            }
            // 修改原有的权限不可用,其余的不需要操作
            for (Object item: notExists) {
                PermissionQuery query = new PermissionQuery();
                query.setId((Long)item);
                PermissionDomain domain = permissionService.getOne(query);
                domain.setAvailable(1);
                permissionService.update(domain);
            }
        }catch (Exception e){
            e.printStackTrace();
            return errorObjectStr("保存失败！");
        }
        return successObjectStr("保存成功!");
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
