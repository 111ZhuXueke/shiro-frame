package org.rj.frame.shiro.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.rui.web.common.enums.PermissionType;
import org.rj.frame.shiro.service.domain.admin.ModuleDomain;
import org.rj.frame.shiro.service.domain.admin.PermissionDomain;
import org.rj.frame.shiro.service.query.PermissionQuery;
import org.rj.frame.shiro.service.service.IPermissionService;
import org.rj.frame.shiro.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 模块控制层
 * @author : zhuxueke
 * @since : 2018/3/13 10:35
 */
@Controller
@Scope("prototype")
@RequestMapping("/module")
public class ModuleController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private IPermissionService permissionService;

    /**
     * 获取所有一级菜单
     * @author : zhuxueke
     * @since : 2018/3/19 9:31
     */
    @RequestMapping(value = "/click")
    @ResponseBody
    public String getClicks(Long moduleId){
        PermissionQuery query = new PermissionQuery();
        query.setModuleId(moduleId);
        query.setType(PermissionType.CLICK.getType());
        query.setAvailable(0);
        List<PermissionDomain> lists = permissionService.getList(query);
        Set<String> permissions = this.getPermissions();
        List<PermissionDomain> arrayList = new ArrayList<>();
        if(lists.size() > 0){
            for (PermissionDomain per:lists) {
                for (String item:permissions) {
                    if(per.getPermission().equals(item)){
                        arrayList.add(per);
                    }
                }
            }
        }
        return JSONObject.toJSONString(arrayList);
    }

    /**
     * 获取所有二级菜单
     * @author : zhuxueke
     * @since : 2018/3/19 9:31
     */
    @RequestMapping(value = "/menu")
    @ResponseBody
    public String getMenus(Long parentId){
        PermissionQuery query = new PermissionQuery();
        query.setType(PermissionType.MENU.getType());
        query.setParentId(parentId);
        List<PermissionDomain> lists = permissionService.getList(query);
        Set<String> permissions = this.getPermissions();
        List<PermissionDomain> arrayList = new ArrayList<>();
        if(lists.size() > 0){
            for (PermissionDomain per:lists) {
                for (String item:permissions) {
                    if(per.getPermission().equals(item)){
                        arrayList.add(per);
                    }
                }
            }
        }
        return JSONObject.toJSONString(arrayList);
    }
}
