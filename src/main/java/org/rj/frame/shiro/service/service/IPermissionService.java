package org.rj.frame.shiro.service.service;

import com.rui.web.common.service.IBaseService;
import org.rj.frame.shiro.service.domain.admin.PermissionDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;

import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:49
 **/
public interface IPermissionService extends IBaseService<PermissionDomain> {
    /**
     * 获取资源对象
     * @author : zhuxueke
     * @since : 2018/3/15 9:43
     */
    Set<PermissionDomain> getPermissionsById(Set<Long> longSet);
}
