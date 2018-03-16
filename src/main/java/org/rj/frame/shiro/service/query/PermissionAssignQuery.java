package org.rj.frame.shiro.service.query;

import com.rui.web.common.persistence.criteria.QueryCriteria;
import com.rui.web.common.query.Query;
import org.rj.frame.shiro.service.domain.admin.ModuleDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:56
 **/
public class PermissionAssignQuery extends Query {

    private Long roleId;
    private Long permissionId;
    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(ModuleDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if(valid(roleId)){
            criteria.andEqualTo("roldId",roleId);
        }
        if(valid(permissionId)){
            criteria.andEqualTo("permissionId",permissionId);
        }
        return queryCriteria;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }
}
