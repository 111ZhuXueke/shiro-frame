package org.rj.frame.shiro.service.query;

import com.rui.web.common.persistence.criteria.QueryCriteria;
import com.rui.web.common.query.Query;
import org.rj.frame.shiro.service.domain.admin.PermissionDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:56
 **/
public class PermissionQuery extends Query {

    private Long id;
    /*权限类型*/
    private String type;

    /*模块ID*/
    private Long moduleId;

    private Long parentId;

    private String name;

    private String url;
    /*是否可用 0:true 1:false*/
    private Integer available;

    public PermissionQuery(){

    }
    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(PermissionDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if(valid(id)){
            criteria.andEqualTo("id",id);
        }
        if(valid(parentId)){
            criteria.andEqualTo("parentId",parentId);
        }
        if(valid(name)){
            criteria.andLike("name","%" + name + "%");
        }
        if(valid(type)){
            criteria.andEqualTo("type",type);
        }
        if (valid(available)){
            criteria.andEqualTo("available",available);
        }
        if (valid(url)){
            criteria.andEqualTo("url",url);
        }

        return queryCriteria;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
