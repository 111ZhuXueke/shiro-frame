package org.rj.frame.shiro.service.service;

import com.rui.web.common.service.IBaseService;
import org.rj.frame.shiro.service.domain.admin.RoleAssignDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;

import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:49
 **/
public interface IRoleAssignService extends IBaseService<RoleAssignDomain> {
    /**
     * 通过登录的用户获取角色对象信息
     * @author : zhuxueke
     * @since : 2018/3/14 16:27
     */
    Set<Long>  getRolesId(UserDomain userDomain);
}
