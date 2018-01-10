package com.rui.web.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rui.web.common.exception.BaseException;
import com.rui.web.common.persistence.Mapper;
import com.rui.web.common.persistence.criteria.QueryCriteria;
import com.rui.web.common.persistence.pager.PageList;
import com.rui.web.common.query.Query;
import com.rui.web.common.service.IBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用实现类
 * @author : zhuxueke
 * @since : 2017-12-08 9:13
 **/
@Service
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private Mapper<T> mapper;

    @Override
    public T get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T getPrimaryKeyObj(Object object) {
        return mapper.selectByPrimaryKey(object);
    }

    @Override
    public T getOne(Query query) {
        List<T> list = this.getList(query);
        if(list == null || list.size() == 0){
            return null;
        }
        if(list.size() == 1){
            return list.get(0);
        }else {
            throw new BaseException(201, "期望查询一条记录，结果返回多条记录");
        }
    }

    @Override
    public T getFirst(Query query) {
        query.setOffset(0);
        query.setLimit(1);
        List<T> list = this.getSliceList(query);
        if (list.size() >= 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<T> getList(Query query) {
        QueryCriteria queryCriteria = query.toCriteria();
        processOrder(query, queryCriteria);
        return mapper.selectByCondition(queryCriteria);
    }

    @Override
    public List<T> getListNoDesc(Query query) {
        QueryCriteria queryCriteria = query.toCriteria();
        return mapper.selectByCondition(queryCriteria);
    }

    @Override
    public PageList<T> getPageList(Query query) {
        if (query.getPageIndex() == null) {
            throw new BaseException(300, "分页查询必须设置PageIndex");
        }
        if (query.getPageSize() == null) {
            throw new BaseException(300, "分页查询必须设置PageSize");
        }
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<T> resultList = this.getList(query);
        return new PageList<T>(resultList, query.getPageIndex(), query.getPageSize(), ((Page) resultList).getTotal()+query.getOffset());
    }

    @Override
    public PageList<T> getPageListNoDesc(Query query) {
        if (query.getPageIndex() == null) {
            throw new BaseException(300, "分页查询必须设置PageIndex");
        }
        if (query.getPageSize() == null) {
            throw new BaseException(300, "分页查询必须设置PageSize");
        }
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<T> resultList = this.getListNoDesc(query);
        return new PageList<T>(resultList, query.getPageIndex(), query.getPageSize(), ((Page) resultList).getTotal()+query.getOffset());
    }

    @Override
    public List<T> getSliceList(Query query) {
        if (query.getLimit() == null) {
            throw new BaseException(300, "Limit未设置");
        }
        if (query.getOffset() == null) {
            throw new BaseException(300, "Offset未设置");
        }
        QueryCriteria queryCriteria = query.toCriteria();
        queryCriteria.setLimit(query.getLimit());
        queryCriteria.setOffset(query.getOffset());
        processOrder(query, queryCriteria);
        return mapper.selectSliceByCriteria(queryCriteria);
    }

    @Override
    public List<T> getSliceListNoDesc(Query query) {
        if (query.getLimit() == null) {
            throw new BaseException(300, "Limit未设置");
        }
        if (query.getOffset() == null) {
            throw new BaseException(300, "Offset未设置");
        }
        QueryCriteria queryCriteria = query.toCriteria();
        queryCriteria.setLimit(query.getLimit());
        queryCriteria.setOffset(query.getOffset());
        return mapper.selectSliceByCriteria(queryCriteria);
    }

    @Override
    public int count(Query query) {
        return mapper.selectCountByCondition(query.toCriteria());
    }

    @Override
    public T create(T domain) {
        int insertCount = mapper.insert(domain);
        if (insertCount != 1) {
            throw new BaseException(300, "数据新增失败");
        }
        return domain;
    }

    @Override
    public void delete(Long id) {
        int deleteCount = mapper.deleteByPrimaryKey(id);
        if (deleteCount != 1) {
            throw new BaseException(300, "数据删除失败");
        }
    }

    @Override
    public void deletePrimaryKeyObj(Object object) {
        int deleteCount = mapper.deleteByPrimaryKey(object);
        if (deleteCount != 1) {
            throw new BaseException(300, "数据删除失败");
        }
    }

    @Override
    public void update(T domain) {
        int updateCount = mapper.updateByPrimaryKeySelective(domain);
        if (updateCount != 1) {
            throw new BaseException(300, "数据更新失败");
        }
    }

    @Override
    public void updateAll(T domain) {
        int updateCount = mapper.updateByPrimaryKey(domain);
        if (updateCount != 1) {
            throw new BaseException(300, "数据更新失败");
        }
    }
    private void processOrder(Query query, QueryCriteria queryCriteria) {
        String orderBy = query.getOrderBy();
        if (StringUtils.isNotBlank(orderBy)) {
            if (query.getDesc()) {
                queryCriteria.orderBy(orderBy).desc();
            } else {
                queryCriteria.orderBy(orderBy).asc();
            }
        } else {
            queryCriteria.orderBy("id").desc();
        }
        query.setOrderBy(orderBy);
    }
}
