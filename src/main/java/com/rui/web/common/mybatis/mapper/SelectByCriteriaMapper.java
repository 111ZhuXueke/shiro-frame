package com.rui.web.common.mybatis.mapper;

import com.rui.web.common.mybatis.provider.CriteriaProvider;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 通用Mapper接口,Condition查询
 * @author : zhuxueke
 * @since : 2017-12-08 9:21
 **/
public interface SelectByCriteriaMapper<T> {
    /**
     * 根据Condition条件进行查询
     *
     * @param condition
     * @return
     */
    @SelectProvider(type = CriteriaProvider.class, method = "dynamicSQL")
    @ResultType(value = List.class)
    List<Map<String, Object>> selectByCriteria(Object condition);
}
