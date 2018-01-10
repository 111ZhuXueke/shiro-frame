package org.rj.frame.shiro.service.domain.admin;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 系统权限信息表
 * @author : zhuxueke
 * @since : 2018-01-10 16:54
 **/
@Table(name = "permission")
public class PermissionDomain {
    /*权限id*/
    @Id
    private Long id;

    /*权限名称*/
    private String name;

    /*权限类型*/
    private String type;

    /*资源路径*/
    private String url;

    /*资源父ID*/
    private Long parentId;

    /*资源父编号列表*/
    private String parentIds;

    /*权限字符串*/
    private String permission;

    /*是否可用 0:true 1:false*/
    private Integer available;

    /*模块ID*/
    private Long moduleId;

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

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public Long getParentId(){
        return parentId;
    }

    public void setParentId(Long parentId){
        this.parentId = parentId;
    }

    public String getParentIds(){
        return parentIds;
    }

    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }

    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission){
        this.permission = permission;
    }

    public Integer getAvailable(){
        return available;
    }

    public void setAvailable(Integer available){
        this.available = available;
    }

    public Long getModuleId(){
        return moduleId;
    }

    public void setModuleId(Long moduleId){
        this.moduleId = moduleId;
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
