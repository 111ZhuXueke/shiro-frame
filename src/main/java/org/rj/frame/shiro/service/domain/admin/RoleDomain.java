package org.rj.frame.shiro.service.domain.admin;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 系统角色表
 * @author : zhuxueke
 * @since : 2018-01-10 16:53
 **/
@Table(name = "role")
public class RoleDomain {
    /*角色id*/
    @Id
    private Long id;

    /*角色名称*/
    private String name;

    /*角色描述*/
    private String description;

    /*是否可用 0:true 1:false*/
    private Integer available;

    /*创建时间*/
    private Date createTime;

    /*修改时间*/
    private Date updateTime;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Integer getAvailable(){
        return available;
    }

    public void setAvailable(Integer available){
        this.available = available;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

}
