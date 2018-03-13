package org.rj.frame.shiro.service.domain.admin;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 角色-资源关联表
 * @author : zhuxueke
 * @since : 2018-03-13 10:10
 **/
@Table(name = "permission_assign")
public class PermissionAssignDomain {
    @Id
    private Long id;

    // 资源id
    private Long permissionId;

    // 角色id
    private Long roleId;

    // 创建时间
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
