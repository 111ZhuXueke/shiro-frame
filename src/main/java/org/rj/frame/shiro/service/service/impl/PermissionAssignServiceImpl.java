package org.rj.frame.shiro.service.service.impl;

import com.rui.web.common.service.impl.BaseServiceImpl;
import org.rj.frame.shiro.service.domain.admin.PermissionAssignDomain;
import org.rj.frame.shiro.service.mapper.IPermissionAssignMapper;
import org.rj.frame.shiro.service.query.PermissionAssignQuery;
import org.rj.frame.shiro.service.service.IPermissionAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:49
 **/
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PermissionAssignServiceImpl extends BaseServiceImpl<PermissionAssignDomain> implements IPermissionAssignService {

    @Autowired
    private IPermissionAssignMapper permissionAssignMapper;

    @Override
    public Set<Long> getPermissionAssign(Set<Long> roleIds) {
        Set<Long> sets = new HashSet<>();
        for (Long item: roleIds) {
            List<PermissionAssignDomain> domainList = super.getList(new PermissionAssignQuery());
            if(domainList != null) {
                for (PermissionAssignDomain permissionAssignDomain : domainList) {
                    sets.add(permissionAssignDomain.getPermissionId());
                }
            }
        }
        return sets;
    }
}
