package org.rj.frame.shiro.service.service.impl;

import com.rui.web.common.service.impl.BaseServiceImpl;
import org.rj.frame.shiro.service.domain.admin.PermissionDomain;
import org.rj.frame.shiro.service.mapper.IPermissionMapper;
import org.rj.frame.shiro.service.query.PermissionQuery;
import org.rj.frame.shiro.service.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:49
 **/
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionDomain> implements IPermissionService {
    @Autowired
    private IPermissionMapper permissionMapper;

    @Override
    public Set<PermissionDomain> getPermissionsById(Set<Long> longSet) {
        Set<PermissionDomain> sets = new HashSet<>();
        for (Long item:longSet) {
            PermissionQuery permissionQuery = new PermissionQuery();
            permissionQuery.setId(item);
            PermissionDomain domain = super.getOne(permissionQuery);
            if (domain != null) sets.add(domain);
        }
        return sets;
    }

    @Override
    public Long getMaxId() {
        return permissionMapper.getMaxId();
    }
}
