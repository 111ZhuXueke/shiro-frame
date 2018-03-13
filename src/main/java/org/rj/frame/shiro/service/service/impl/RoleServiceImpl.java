package org.rj.frame.shiro.service.service.impl;

import com.rui.web.common.service.impl.BaseServiceImpl;
import org.rj.frame.shiro.service.domain.admin.RoleDomain;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.rj.frame.shiro.service.mapper.UserMapper;
import org.rj.frame.shiro.service.service.IRoleService;
import org.rj.frame.shiro.service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:49
 **/
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDomain> implements IRoleService {

}
