package org.rj.frame.shiro.service.service.impl;

import com.rui.web.common.enums.RoleAvailable;
import com.rui.web.common.service.impl.BaseServiceImpl;
import org.rj.frame.shiro.service.domain.admin.RoleDomain;
import org.rj.frame.shiro.service.mapper.IRoleMapper;
import org.rj.frame.shiro.service.query.RoleQuery;
import org.rj.frame.shiro.service.service.IRoleService;
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
public class RoleServiceImpl extends BaseServiceImpl<RoleDomain> implements IRoleService {
    @Autowired
    private IRoleMapper roleMapper;
    @Override
    public Set<RoleDomain> getRoles(Set<Long> roleIds) {
        Set<RoleDomain> sets = new HashSet<>();
        for (Long item:roleIds) {
            try{
                RoleDomain roleDomain = super.getOne(new RoleQuery(item,null, RoleAvailable.OPEN.getCode()));
                if (roleDomain != null) sets.add(roleDomain);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return sets;
    }
}
