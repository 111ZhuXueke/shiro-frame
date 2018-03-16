package org.rj.frame.shiro.service.service;

import com.rui.web.common.service.IBaseService;
import org.rj.frame.shiro.service.domain.admin.UserDomain;

import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:49
 **/
public interface IUserService extends IBaseService<UserDomain> {
     UserDomain queryUserByName(String userName);
     Set<String> queryRolesByName(String userName);
}
