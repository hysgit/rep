package com.woslx.rep.rep.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ItemType {
    private Integer id;

    private String name;

    @JSONField(serialize=false)
    private Date createTime;

    @JSONField(serialize=false)
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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