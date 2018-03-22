package org.rj.frame.shiro.service.service.impl;

import com.rui.web.common.service.impl.BaseServiceImpl;
import org.rj.frame.shiro.service.domain.admin.RoleAssignDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.rj.frame.shiro.service.mapper.IRoleAssignMapper;
import org.rj.frame.shiro.service.query.RoleAssignQuery;
import org.rj.frame.shiro.service.service.IRoleAssignService;
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
public class RoleAssignServiceImpl extends BaseServiceImpl<RoleAssignDomain> implements IRoleAssignService {

    @Autowired
    private IRoleAssignMapper roleAssignMapper;
    @Override
    public Set<Long> getRolesId(UserDomain userDomain) {
        Set<Long> stringSet = new HashSet<>();
        if(userDomain != null){
            List<RoleAssignDomain> lists = super.getList(new RoleAssignQuery(userDomain.getId()));
            if(null != lists) {
                for (RoleAssignDomain roleAssignDomain : lists) {
                    stringSet.add(roleAssignDomain.getRoleId());
                }
            }
        }
        return stringSet;
    }

    @Override
    public Long getMaxId() {
        return null;
    }
}
