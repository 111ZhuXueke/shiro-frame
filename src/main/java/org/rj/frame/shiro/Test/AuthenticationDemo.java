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

/**
 * @author : zhuxueke
 * @since : 2018-01-02 14:06
 **/
public class AuthenticationDemo {
    @Test
    public void testRun(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取 SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try{
            subject.login(token);
            System.out.println("success");
        }catch (AuthenticationException e) {
            //身份验证失败
            e.printStackTrace();
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        //Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        subject.logout();
    }
}
