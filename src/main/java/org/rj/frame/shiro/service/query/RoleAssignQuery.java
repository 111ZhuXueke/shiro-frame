package org.rj.frame.shiro.service.query;

import com.rui.web.common.persistence.criteria.QueryCriteria;
import com.rui.web.common.query.Query;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:56
 **/
public class RoleAssignQuery extends Query {

    public RoleAssignQuery(Integer id){
        this.id = id;
    }

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(UserDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if(valid(id)){
            criteria.andEqualTo("id",id);
        }
        return queryCriteria;
    }
}
