package com.rui.web.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * model 额外类
 * @author : zhuxueke
 * @since : 2017-12-12 16:24
 **/
public abstract class Query {
    @JSONField(serialize=false)
    private Integer pageIndex= 1;//当前页码
    @JSONField(serialize=false)
    private Integer pageSize = 20; //页面大小，默认20
    @JSONField(serialize=false)
    private Integer offset = 0; // 行偏移
    @JSONField(serialize=false)
    private Integer limit;    //获取最大数量
    @JSONField(serialize=false)
    private String orders;// 排序字段
    @JSONField(serialize=false)
    private Boolean isDesc = true;// 是否倒序，默认是
    public Integer getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getOffset() {
        return (this.getPageIndex() - 1) * this.getPageSize();
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        if (limit > 500)
            limit = 500;
        this.limit = limit;
    }
    public String getOrders() {
        return orders;
    }
    public void setOrders(String orders) {
        this.orders = orders;
    }
    public Boolean getIsDesc() {
        return isDesc;
    }
    public void setIsDesc(Boolean isDesc) {
        this.isDesc = isDesc;
    }
}
