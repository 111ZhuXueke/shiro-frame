package org.rj.frame.shiro.service.service;

import com.rui.web.common.service.IBaseService;
import org.rj.frame.shiro.service.domain.admin.RoleDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;

import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:49
 **/
public interface IRoleService extends IBaseService<RoleDomain> {
    /**
     * 获取权限表详细信息
     * @author : zhuxueke
     * @since : 2018/3/15 9:09
     */
    Set<RoleDomain> getRoles(Set<Long> roleIds);
}
