package org.rj.frame.shiro.service.service.impl;

import com.rui.web.common.service.impl.BaseServiceImpl;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.rj.frame.shiro.service.mapper.IUserMapper;
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
public class UserServiceImpl extends BaseServiceImpl<UserDomain> implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public UserDomain queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);
    }

    @Override
    public Set<String> queryRolesByName(String userName) {
        return userMapper.queryRolesByName(userName);
    }

    @Override
    public Long getMaxId() {
        return null;
    }
}
