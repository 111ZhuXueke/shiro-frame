package org.rj.frame.shiro.web.controller.base;

import com.rui.web.common.enums.Constant;
import com.rui.web.common.model.AdminModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Set;

/**
 * 管理员对象的上下文对象
 * @author : zhuxueke
 * @since : 2018-03-13 13:17
 **/
@Component
public class AdminContext implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 管理员对象
    private AdminModel adminModel;

    // 角色列表
    private Set<String> roleSet;

    // 权限资源
    private Set<String> permissions;

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public void setAdminModel(AdminModel adminModel) {
        this.adminModel = adminModel;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    /**
     * 获取上下文信息
     * @author : zhuxueke
     * @since : 2018/3/13 13:19
     */
    public AdminContext getCurrent() {
        AdminContext adminContext = new AdminContext();
        Subject subject = SecurityUtils.getSubject();
        if (subject != null){
            Session session = subject.getSession();
            if(session != null){
                Object object = session.getAttribute(Constant.CURRENT);
                if(null != object){
                    adminContext = (AdminContext) object;
                }
            }
        }
        return adminContext;
    }
}
