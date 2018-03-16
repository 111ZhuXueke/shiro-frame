package org.rj.frame.shiro.service.query;

import com.rui.web.common.persistence.criteria.QueryCriteria;
import com.rui.web.common.query.Query;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:56
 **/
public class RoleQuery extends Query {
    private Long roleId;
    private String name;
    /*是否可用 0:true 1:false*/
    private Integer available;

    public RoleQuery(Long roleId,String name,Integer available){
        this.name = name;
        this.roleId = roleId;
        this.available = available;
    }
    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(UserDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if(valid(roleId)){
            criteria.andEqualTo("id",roleId);
        }
        if(valid(name)){
            criteria.andEqualTo("name",name);
        }
        if(valid(available)) criteria.andEqualTo("available",available);
        return queryCriteria;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
