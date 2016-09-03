package com.woslx.rep.rep.entity;

import java.util.Date;

public class Records {
    private Integer id;

    private Integer itemId;

    private Integer actionType;

    private Integer actionDetail;

    private String actionDesc;

    private String srcOrDst;

    private Integer quantity;

    private Date time;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public Integer getActionDetail() {
        return actionDetail;
    }

    public void setActionDetail(Integer actionDetail) {
        this.actionDetail = actionDetail;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc == null ? null : actionDesc.trim();
    }

    public String getSrcOrDst() {
        return srcOrDst;
    }

    public void setSrcOrDst(String srcOrDst) {
        this.srcOrDst = srcOrDst == null ? null : srcOrDst.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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