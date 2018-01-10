package com.rui.web.common.mybatis;

import com.rui.web.common.mybatis.mapper.SelectByCriteriaMapper;
import com.rui.web.common.mybatis.mapper.criteria.SelectSliceByCriteriaMapper;
import tk.mybatis.mapper.common.*;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author : zhuxueke
 * @since : 2017-12-08 9:20
 **/
public interface CommonMapper<T> extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T>,
        RowBoundsMapper<T>,
        SelectByCriteriaMapper<T>,
        SelectSliceByCriteriaMapper<T>,
        Marker
{
}
