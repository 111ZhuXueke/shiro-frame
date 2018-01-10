package org.rj.frame.shiro.Test;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;


/**
 * 查看用户是否拥有某个权限
 * @author : zhuxueke
 * @since : 2018-01-03 16:19
 **/
public class RoleDemo {
    @Test
    public void roleRun(){
        Subject subject = Authentication.login("classpath:shiro.ini", "zhang", "123");
        if(!subject.hasRole("role1")){
            System.out.println("role1 不通过");
        }
        if(!subject.hasRole("role2")){
            System.out.println("role2 不通过");
        }
        boolean[] result = subject.hasRoles(Arrays.asList("role3"));
        if(!result[0]){
            System.out.println("role3 不通过!");
        }

    }


}
