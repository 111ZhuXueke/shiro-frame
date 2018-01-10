package com.rui.web.common.model;

/**
 * @author : zhuxueke
 * @since : 2017-12-13 14:37
 **/
public class AdminModel<T> implements java.io.Serializable {

    /*管理员唯一标识 Long 类型*/
    private Long Id;

    /*管理员唯一标识 String类型*/
    private String adminId;

    /*名称*/
    private String realName;

    /*用户名*/
    private String userName;

    /*密码*/
    private String password;

    /*盐*/
    private String salt;

    /*是否可用 0:true 1:false*/
    private Integer locked;

    /*最后登录ip*/
    private String lastLoginIp;

    /*登录次数*/
    private Integer loginCount;

    /* 泛型对象 */
    private T obj;

    public AdminModel() {
        super();
    }

    public AdminModel(
            Long Id, String adminId, String realName, String userName, String password, String salt,
            Integer locked, String lastLoginIp, Integer loginCount) {
        super();
        this.Id = Id;
        this.adminId = adminId;
        this.realName = realName;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.locked = locked;
        this.lastLoginIp = lastLoginIp;
        this.loginCount = loginCount;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
