package org.rj.frame.shiro.Test;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author : zhuxueke
 * @since : 2018-01-03 16:33
 **/
public class PermissionDemo {

    /**
     * isPermitted 返回false 不会抛出异常
     * @author : zhuxueke
     * @since : 2018/1/3 16:38
     */
    @Test
    public void permittedRun(){
        Subject subject = Authentication.login("classpath:shiro.ini", "zhang", "123");
        if(!subject.isPermitted("user:create")){
            System.out.println("没有 create 权限");
        }
        if(!subject.isPermitted("user:view")){
            System.out.println("没有 view 权限");
        }
        if(!subject.isPermittedAll("user.update","user:delete")){
            System.out.println("没有 update/delete 权限");
        }
    }

    /**
     * checkPermission 不为true 会抛出异常
     * @author : zhuxueke
     * @since : 2018/1/3 16:38
     */
    @Test
    public void checkPermission(){
        Subject subject = Authentication.login("classpath:shiro.ini", "zhang", "123");
        try{
            subject.checkPermissions("user:create");
            System.out.println("没有 create 权限");
            subject.checkPermissions("user:view");
            System.out.println("没有 view 权限");
            subject.checkPermissions("user.update","user:delete");
            System.out.println("没有 update/delete 权限");
        }catch (UnauthorizedException e){
            System.out.println("出异常了!");
        }

    }
}
