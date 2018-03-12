package org.rj.frame.shiro.web.controller.system;

import com.rui.web.common.enums.PermissionType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rj.frame.shiro.web.util.ShiroPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  权限资源管理
 * @author : zhuxueke
 * @since : 2018-03-12 17:17
 **/
@ShiroPermissions(name = "系统设置",type = PermissionType.CLICK, moduleLabel = "system")
@RequiresPermissions("system:permissions:manager")
@Scope("prototype")
@Controller
@RequestMapping(value = "system/permissions")
public class Permissions {

}
