package org.rj.frame.shiro.service.domain.admin;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 模块表
 * @author : zhuxueke
 * @since : 2018-03-13 10:05
 **/
@Table(name = "module")
public class ModuleDomain {
    @Id
    private Long id;

    /*模块名称*/
    private String name;

    /*描述*/
    private String description;

    /*模块标签*/
    private String lable;

    /*状态：1开启、0关闭 */
    private Long status;

    /*排序字段*/
    private Long sorting;

    /*创建时间*/
    private Date createTime;

    /*最后修改时间*/
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getSorting() {
        return sorting;
    }

    public void setSorting(Long sorting) {
        this.sorting = sorting;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
