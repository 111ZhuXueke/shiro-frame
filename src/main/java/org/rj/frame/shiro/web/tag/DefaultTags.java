package org.rj.frame.shiro.web.tag;

import com.rui.web.common.enums.PermissionType;
import com.rui.web.common.model.AdminModel;
import com.rui.web.common.security.user.AdminContext;
import org.rj.frame.shiro.service.domain.admin.ModuleDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统默认标签库
 * @author : zhuxueke
 * @since : 2018-03-16 15:59
 **/
public class DefaultTags {

    public DefaultTags(AdminContext context){
        adminContexts = context;
    }
    private static AdminContext adminContexts;

    /**
     * 获取用户信息
     * @author : zhuxueke
     * @since : 2018/3/16 16:10
     */
    public static AdminModel getAdmin() {
        AdminModel adminModel = adminContexts.getCurrent().getAdminModel();
        if(adminModel == null) {
            new Exception("登陆信息异常");
        }
        return adminModel;
    }

    /**
     * 获取菜单模块
     * @author : zhuxueke
     * @since : 2018/3/16 16:10
     */
    public static List<ModuleDomain> getModules() {
        List<ModuleDomain> moduleModels = new ArrayList<ModuleDomain>();
        AdminContext adminContext1 = adminContexts.getCurrent();
        if(adminContext1 != null)
            moduleModels = adminContext1.getModuleDomains();
        return moduleModels;
    }

    /**
     * 获取权限级别名称
     * @author : zhuxueke
     * @since : 2018/3/21 14:42
     */
    public static List<String> getEnums(){
        List<String> enums = new ArrayList<>();
        PermissionType[] strs = PermissionType.values();
        for (PermissionType item:strs) {
            enums.add(item.getType());
        }
        return enums;
    }
}
