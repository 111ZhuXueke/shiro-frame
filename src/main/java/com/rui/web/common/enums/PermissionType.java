package com.rui.web.common.enums;

public enum PermissionType {
    CLICK("click"),MENU("menu"),BUTTON("button");

    private String type;

    PermissionType(String type){
        this.type = type;
    }

    /**
     * 资源类型
     * @author : zhuxueke
     * @since : 2018/3/12 16:39
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
