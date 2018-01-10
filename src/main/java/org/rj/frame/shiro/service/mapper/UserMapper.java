package org.rj.frame.shiro.service.mapper;

import com.rui.web.common.persistence.Mapper;
import org.rj.frame.shiro.service.domain.admin.UserDomain;

import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:47
 **/
public interface UserMapper extends Mapper<UserDomain> {
    UserDomain queryUserByName(String userName);
    Set<String> queryRolesByName(String userName);
}
