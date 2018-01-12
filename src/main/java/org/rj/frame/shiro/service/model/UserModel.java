package org.rj.frame.shiro.service.model;

import com.rui.web.common.model.Query;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:57
 **/
public class UserModel extends Query implements java.io.Serializable {
    private String userName;
    private String password;

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
}
