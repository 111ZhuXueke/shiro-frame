package org.rj.frame.shiro.service.service;

import com.rui.web.common.service.IBaseService;
import org.rj.frame.shiro.service.domain.admin.PermissionAssignDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;

import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:49
 **/
public interface IPermissionAssignService extends IBaseService<PermissionAssignDomain> {
    /**
     * 根据权限获取资源编号
     * @author : zhuxueke
     * @since : 2018/3/15 9:36
     */
    public Set<Long> getPermissionAssign(Set<Long> roleIds);
}
