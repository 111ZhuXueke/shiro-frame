package org.rj.frame.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.rj.frame.shiro.service.query.UserQuery;
import org.rj.frame.shiro.service.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义域realm
 * @author : zhuxueke
 * @since : 2018-01-10 15:30
 **/
public class CustomRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    private IUserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        UserQuery userQuery = new UserQuery();
        userQuery.setUserName(token.getUsername());
        UserDomain user = userService.getFirst(userQuery);
//        UserDomain user = new UserDomain();
//        user.setUserName("admin");
//        user.setPassword("123456");
        if (user == null) {
            throw new AuthorizationException();
        }
        SimpleAuthenticationInfo info = null;
        if (user.getUserName().equals(token.getUsername())) {
            info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        }
        return  info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 根据用户配置用户与权限
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        String name = (String) getAvailablePrincipal(principals);
        List<String> roles = new ArrayList<String>();
        UserDomain user = userService.queryUserByName(name);
        // 简单默认一个用户与角色，实际项目应User user = userService.getByAccount(name);
//        UserDomain user = new UserDomain();
//        user.setUserName("admin");
//        user.setPassword("123456");
        if (user.getUserName().equals(name)) {

        } else {
            throw new AuthorizationException();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 增加角色
        info.addRoles(roles);
        return info;
    }
}
