package com.rui.web.common.mybatis.mapper.criteria;

import com.rui.web.common.mybatis.provider.CriteriaProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : zhuxueke
 * @since : 2017-12-08 9:23
 **/
public interface SelectSliceByCriteriaMapper<T> {

    /**
     * 根据Condition条件进行查询
     * @param condition
     * @return
     */
    @SelectProvider(type = CriteriaProvider.class, method = "dynamicSQL")
    List<T> selectSliceByCriteria(Object condition);
}
