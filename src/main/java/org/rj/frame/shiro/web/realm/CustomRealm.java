package org.rj.frame.shiro.web.realm;

import com.rui.web.common.enums.Constant;
import com.rui.web.common.enums.PermissionType;
import com.rui.web.common.model.AdminModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.rj.frame.shiro.service.domain.admin.ModuleDomain;
import org.rj.frame.shiro.service.domain.admin.PermissionDomain;
import org.rj.frame.shiro.service.domain.admin.RoleDomain;
import org.rj.frame.shiro.service.query.ModuleQuery;
import org.rj.frame.shiro.service.service.*;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.rj.frame.shiro.service.query.UserQuery;
import com.rui.web.common.security.user.AdminContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 自定义域realm
 * @author : zhuxueke
 * @since : 2018-01-10 15:30
 **/
public class CustomRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    private AdminContext adminContext;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleAssignService roleAssignService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionAssignService permissionAssignService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IModuleService moduleService;
    /**
     * 登录认证
     * @author : zhuxueke
     * @since : 2018/3/13 13:09
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 把token转换成User对象
        UserQuery userLogin = tokenToUser((UsernamePasswordToken) authcToken);
        UserDomain ui = userService.getOne(userLogin);
        if(ui == null) return null; // 异常处理，找不到数据
        //当前 Realm 的 name
        String realmName = this.getName();
        //登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.
        Object principal = authcToken.getPrincipal();
        // 设置用户信息
        AdminModel adminModel = new AdminModel();
        adminModel.setObj(ui);
        adminContext.setAdminModel(adminModel);

        //绑定角色对象
        Set<Long> roleIds = roleAssignService.getRolesId(ui);
        Set<RoleDomain> roleDomains = roleService.getRoles(roleIds);
        //角色处理
        Set<String> roleSet = new HashSet<String>();
        for (RoleDomain roleDomain : roleDomains) {
            roleSet.add(roleDomain.getName());
        }
        // 将角色存入 adminContext中
        adminContext.setRoleSet(roleSet);//角色

        // 权限对象
        Set<Long> permissionIds = permissionAssignService.getPermissionAssign(roleIds);
        Set<PermissionDomain> permissionDomains = permissionService.getPermissionsById(permissionIds);
        Set<String> permissions = new HashSet<String>();
        for (PermissionDomain item:permissionDomains) {
            permissions.add(item.getPermission());
        }
        // 将权限字符串存入 adminContext中
        adminContext.setPermissions(permissions);

        // 获取模块
        Set<Long> set = new HashSet<Long>();
        for(PermissionDomain item:permissionDomains){
            //筛选出所有模块是click 的对象
            if(item != null && item.getType().equals(PermissionType.CLICK.getType())) set.add(item.getModuleId());
        }
        List<Long> idList = new ArrayList<Long>(set);
        List<ModuleDomain> moduleDomains = null;
        if(idList.size() > 0) {
            try{
                moduleDomains = moduleService.getList(new ModuleQuery(idList, false, "sorting"));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        modulePermission(moduleDomains);
        adminContext.setModuleDomains(moduleDomains);

        setSession(Constant.CURRENT,adminContext);
        return new SimpleAuthenticationInfo(principal, userLogin.getPassword(), realmName);
    }


    /**
     * 权限认证
     * @author : zhuxueke
     * @since : 2018/3/13 13:10
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        String username = (String)arg0.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(StringUtils.isEmpty(username)){
            return info;
        }
        info.setRoles(adminContext.getCurrent().getRoleSet());
        info.setStringPermissions(adminContext.getCurrent().getPermissions());
        return info;
    }

    /**
     * 取出token中的用户信息，放在本地对象中
     * @author : zhuxueke
     * @since : 2018/3/15 14:52
     */
    private UserQuery tokenToUser(UsernamePasswordToken authcToken) {
        UserQuery user = new UserQuery();
        user.setUserName(authcToken.getUsername());
        user.setPassword(String.valueOf(authcToken.getPassword()));
        return user;
    }

    /**
     * 对集合排序，获取所有模块
     * @author : zhuxueke
     * @since : 2018/3/15 14:24
     */
    private void modulePermission(List<ModuleDomain> moduleDomains){
        if(moduleDomains == null) return;
        List<ModuleDomain> lists = new ArrayList<>();
        ModuleDomain domain = moduleDomains.get(0);
        lists.add(moduleDomains.get(0));
        for (ModuleDomain item: moduleDomains) {
            if(item.getId() != domain.getId()){
                lists.add(item);
                domain = item;
            }
        }
        // 0、-1 决定正序或倒叙
        lists.sort((ModuleDomain x,ModuleDomain y)-> x.getId() > y.getId()? 0:-1);
    }

    /**
     * 将用户信息保存至session
     * @author : zhuxueke
     * @since : 2018/3/15 14:56
     *
     */
    public void setSession(Object key,Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            if(null != session){
                session.setAttribute(key,value);
            }
        }
    }
}
