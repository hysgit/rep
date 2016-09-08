package com.woslx.rep.rep.entity;

import java.util.Date;

public class Records {
    private Integer id;

    private Integer itemId;

    private Integer itemNameId;

    private Integer itemTypeId;

    private Integer actionType;

    private Integer actionDetail;

    private String actionDesc;

    private String srcOrDst;

    private Integer quantity;

    private Date time;

    private String imgUrl;

    private Integer state;

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

    public Integer getItemNameId() {
        return itemNameId;
    }

    public void setItemNameId(Integer itemNameId) {
        this.itemNameId = itemNameId;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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