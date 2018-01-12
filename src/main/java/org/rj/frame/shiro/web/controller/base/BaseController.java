package org.rj.frame.shiro.web.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhuxueke
 * @since : 2018-01-12 11:09
 **/
public class BaseController implements java.io.Serializable{

    /**
     * success
     * @author : zhuxueke
     * @since : 2018/1/12 11:21
     */
    protected String successObjectStr(String message) {
//        if (object == null) {
//            ExceptionUtils.throwBaseException("参数异常");
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("ok", 200);
//        map.put("message", message);
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
//        if (object == null) {
//            ExceptionUtils.throwBaseException("参数异常");
//        }
        Map<String, Object> map = new HashMap<>();
        map.put("error", 300);
        map.put("message", message);
        return toJSONFormatString(map);
    }

    protected String toJSONFormatString(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
    }
}
