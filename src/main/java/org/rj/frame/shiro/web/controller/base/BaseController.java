package org.rj.frame.shiro.web.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rui.web.common.model.AdminModel;
import com.rui.web.common.security.user.AdminContext;
import org.rj.frame.shiro.service.domain.admin.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : zhuxueke
 * @since : 2018-01-12 11:09
 **/
public class BaseController implements java.io.Serializable{

    @Autowired
    private AdminContext adminContext;
    /**
     * success
     * @author : zhuxueke
     * @since : 2018/1/12 11:21
     */
    protected String successObjectStr(String message) {
        JSONObject json = new JSONObject();
        json.put("ok",200);
        json.put("message",message);
        return json.toJSONString();
    }

    /**
     * error
     * @author : zhuxueke
     * @since : 2018/1/12 11:21
     */
    protected String errorObjectStr(String message) {
        if (message == null) {
            new RuntimeException("登录信息异常");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("error", 300);
        map.put("message", message);
        return toJSONFormatString(map);
    }

    protected String toJSONFormatString(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * 获取用户的所有权限
     * @author : zhuxueke
     * @since : 2018/3/19 12:45
     */
    protected Set<String> getPermissions(){
        return adminContext.getPermissions();
    }
    /**
     * 获得当前登录用户
     * @author : zhuxueke
     * @since : 2018/3/13 10:52
     */
    protected AdminModel<UserDomain> getAdmin() {
        AdminModel<UserDomain> adminModel = adminContext.getCurrent().getAdminModel();
        if (adminModel == null) {
            new RuntimeException("登录信息异常");
        }
        return adminModel;
    }
}
