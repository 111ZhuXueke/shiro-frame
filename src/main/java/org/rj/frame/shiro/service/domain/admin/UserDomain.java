package org.rj.frame.shiro.service.domain.admin;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 管理员信息表
 * @author : zhuxueke
 * @since : 2018-01-10 15:50
 **/
@Table(name = "user")
public class UserDomain {
    /*管理员id*/
    @Id
    private Integer id;

    /*姓名*/
    private String realName;

    /*用户名*/
    private String userName;

    /*密码*/
    private String password;

    /*盐*/
    private String salt;

    /*手机*/
    private String phone;

    /*邮箱*/
    private String email;

    /*是否可用 0:true 1:false*/
    private Integer locked;

    /*描述*/
    private String description;

    /*最后登录时间*/
    private Date lastLoginTime;

    /*最后登录ip*/
    private String lastLoginIp;

    /*登录次数*/
    private Integer loginCount;

    /*创建时间*/
    private Date createTime;

    /*最后修改信息时间*/
    private Date updateTime;

    /*创建人*/
    private Long createAdmin;

    /*最后修改人*/
    private Long updateAdmin;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getRealName(){
        return realName;
    }

    public void setRealName(String realName){
        this.realName = realName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getSalt(){
        return salt;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Integer getLocked(){
        return locked;
    }

    public void setLocked(Integer locked){
        this.locked = locked;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Date getLastLoginTime(){
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime){
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp(){
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp){
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getLoginCount(){
        return loginCount;
    }

    public void setLoginCount(Integer loginCount){
        this.loginCount = loginCount;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Long getCreateAdmin(){
        return createAdmin;
    }

    public void setCreateAdmin(Long createAdmin){
        this.createAdmin = createAdmin;
    }

    public Long getUpdateAdmin(){
        return updateAdmin;
    }

    public void setUpdateAdmin(Long updateAdmin){
        this.updateAdmin = updateAdmin;
    }

}
