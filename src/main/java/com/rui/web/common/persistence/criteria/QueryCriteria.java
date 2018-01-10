package com.rui.web.common.persistence.criteria;

import tk.mybatis.mapper.entity.Example;

/**
 * 通用查询条件
 * @author : zhuxueke
 * @since : 2017-12-07 17:08
 **/
public class QueryCriteria extends Example {

    /*是否只查一条*/
    private boolean selectFirst = false;
    /*当前页索引*/
    private int pageIndex = 1;
    /*页面大小*/
    private int pageSize = 20;
    /*指定第一个返回记录行的偏移量*/
    private Integer offset = 0;
    /*返回记录行的最大数目*/
    private Integer limit;
    /*版本号，用于乐观锁控制*/
    private Object version;

    public QueryCriteria(Class<?> entityClass) {
        super(entityClass);
    }

    public QueryCriteria(Class<?> entityClass, boolean exists) {
        super(entityClass, exists);
    }

    public QueryCriteria(Class<?> entityClass, boolean exists, boolean notNull) {
        super(entityClass, exists, notNull);
    }

    public boolean isSelectFirst() {
        return selectFirst;
    }

    public void setSelectFirst(boolean selectFirst) {
        this.selectFirst = selectFirst;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

}
