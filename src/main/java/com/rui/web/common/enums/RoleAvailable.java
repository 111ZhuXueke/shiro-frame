package com.rui.web.common.enums;

/**
 * 开启权限
 * @author : zhuxueke
 * @since : 2018/3/15 9:13
 */
public enum RoleAvailable {

    OPEN(0, "启用"),
    SHUTDOWN(1, "关闭");

    private int code;
    private String description;
    RoleAvailable(int code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 获取value值
     * @author : zhuxueke
     * @since : 2018/3/15 9:16
     */
    public static RoleAvailable valueOf(int index){
        for (RoleAvailable type:values()){
            if (type.getCode() == index) return type;
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
