package com.rui.web.common.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 顶级异常对象
 * @author : zhuxueke
 * @since : 2017-12-08 9:26
 **/
public class BaseException extends RuntimeException {
    /**
     * 状态码
     */
    private int code;

    /**
     * 扩展参数
     */
    private Map<Object,Object> extendMap;

    public BaseException(int code, String message){
        super(message);
        this.code = code;
    }

    public void put(Object key, Object value){
        if(this.extendMap == null){
            extendMap = new HashMap<Object, Object>();
        }
        this.extendMap.put(key,value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Object key) {
        if (extendMap == null) {
            return null;
        }
        return (T) this.extendMap.get(key);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<Object, Object> getExtendMap() {
        return extendMap;
    }

    public void setExtendMap(Map<Object, Object> extendMap) {
        this.extendMap = extendMap;
    }
}
