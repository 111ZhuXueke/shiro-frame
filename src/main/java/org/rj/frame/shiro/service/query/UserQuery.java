package org.rj.frame.shiro.service.query;

import com.rui.web.common.persistence.criteria.QueryCriteria;
import com.rui.web.common.query.Query;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:56
 **/
public class UserQuery extends Query {
    private String userName;
    private String password;
    private Long id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(UserDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if (valid(userName)){
            criteria.andEqualTo("userName",userName);
        }
        if(valid(password)){
            criteria.andEqualTo("password",password);
        }
        if (valid(id)){
            criteria.andEqualTo("id",id);
        }
        return queryCriteria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
