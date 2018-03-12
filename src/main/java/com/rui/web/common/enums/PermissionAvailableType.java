package com.rui.web.common.enums;

/**
 * 权限资源 状态 枚举
 * @author : zhuxueke
 * @since : 2018/3/12 17:02
 */
public enum PermissionAvailableType {
    ON(0, "可用"), OFF(1, "不可用");

    private int value;
    private String description;

    PermissionAvailableType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
