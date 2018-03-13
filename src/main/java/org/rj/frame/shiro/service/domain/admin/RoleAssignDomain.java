package org.rj.frame.shiro.service.domain.admin;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 系统管理员角色关联授权表
 * @author : zhuxueke
 * @since : 2018-01-10 16:55
 **/
@Table(name = "role_assign")
public class RoleAssignDomain {
    /*角色分配id*/
    @Id
    private Long id;

    /*角色id*/
    private Long roleId;

    /*管理员id*/
    private Long adminId;

    /*创建时间*/
    private Date createTime;

    /*最后修改时间*/
    private Date updateTime;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getRoleId(){
        return roleId;
    }

    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }

    public Long getAdminId(){
        return adminId;
    }

    public void setAdminId(Long adminId){
        this.adminId = adminId;
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
