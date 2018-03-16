package org.rj.frame.shiro.service.query;

import com.rui.web.common.persistence.criteria.QueryCriteria;
import com.rui.web.common.query.Query;
import org.rj.frame.shiro.service.domain.admin.ModuleDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:56
 **/
public class ModuleQuery extends Query {

    private List<Long> idList;
    public ModuleQuery(List<Long> idList,boolean desc,String orderBy){
        super();
        this.idList = idList;
        this.setDesc(desc);
        this.setOrderBy(orderBy);
    }
    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(ModuleDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if(valid(idList)){
            criteria.andIn("id",idList);
        }
        return queryCriteria;
    }

    public List<Long> getIdList() {
        return idList;
    }
}
