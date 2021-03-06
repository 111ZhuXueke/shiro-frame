package com.rui.web.common.persistence;

import com.rui.web.common.mybatis.CommonMapper;

/**
 * 通用Mapper 基类
 * @author : zhuxueke
 * @since : 2017-12-08 9:15
 **/
public interface Mapper<T> extends CommonMapper<T> {
    /**
     * 获取最大Id
     * @author : zhuxueke
     * @since : 2018/3/21 16:14
     */
    Long getMaxId();
}
