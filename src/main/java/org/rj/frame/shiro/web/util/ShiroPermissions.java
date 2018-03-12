package org.rj.frame.shiro.web.util;

import com.rui.web.common.enums.PermissionAvailableType;
import com.rui.web.common.enums.PermissionType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShiroPermissions {
    /**
     * 菜单名称
     * @author : zhuxueke
     * @since : 2018/3/12 16:28
     */
    String name() default "";

    /**
     *  菜单类型
     * @author : zhuxueke
     * @since : 2018/3/12 16:59
     */
    PermissionType type() default PermissionType.MENU;

    /**
     * 父级权限字符串
     * @author : zhuxueke
     * @since : 2018/3/12 17:01
     */
    String parentPermissions() default "";

    /**
     * 模块标签
     * @author : zhuxueke
     * @since : 2018/3/12 17:01
     */
    String moduleLabel() default "";

    /**
     * 权限字符串
     * @author : zhuxueke
     * @since : 2018/3/12 17:02
     */
    String permissions() default "";

    /**
     * 资源是否可用
     * @author : zhuxueke
     * @since : 2018/3/12 17:04
     */
    PermissionAvailableType available() default PermissionAvailableType.ON;
}
