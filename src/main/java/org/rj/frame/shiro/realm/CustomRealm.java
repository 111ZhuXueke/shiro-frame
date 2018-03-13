package org.rj.frame.shiro.realm;

import com.rui.web.common.enums.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.rj.frame.shiro.web.controller.base.AdminContext;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.rj.frame.shiro.service.query.UserQuery;
import org.rj.frame.shiro.service.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
        // 设置session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(Constant.CURRENT, ui);
        //当前 Realm 的 name
        String realmName = this.getName();
        //登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.
        Object principal = authcToken.getPrincipal();
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

    private UserQuery tokenToUser(UsernamePasswordToken authcToken) {
        UserQuery user = new UserQuery();
        user.setUserName(authcToken.getUsername());
        user.setPassword(String.valueOf(authcToken.getPassword()));
        return user;
    }
}
