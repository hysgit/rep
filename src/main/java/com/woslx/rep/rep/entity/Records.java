package com.woslx.rep.rep.entity;

import java.util.Date;

public class Records {
    private Integer id;

    private Integer itemId;

    private Integer itemNameId;

    private Integer itemTypeId;

    private Integer actionType;

    private Integer actionDetail;

    private String transactionalNumber;

    private String srcOrDst;

    private Integer quantityBefore;

    private Integer quantity;

    private Integer quantityAfter;

    private Integer price;

    private Integer pricePutIn;

    private Integer allPrice;

    private Integer allPricePutIn;

    private Date time;

    private String imgUrl;

    private String docterName;

    private String gentaiName;

    private String patientName;

    private String zhuyuanNo;

    private Integer state;

    private String actionDesc;

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

    public String getTransactionalNumber() {
        return transactionalNumber;
    }

    public void setTransactionalNumber(String transactionalNumber) {
        this.transactionalNumber = transactionalNumber == null ? null : transactionalNumber.trim();
    }

    public String getSrcOrDst() {
        return srcOrDst;
    }

    public void setSrcOrDst(String srcOrDst) {
        this.srcOrDst = srcOrDst == null ? null : srcOrDst.trim();
    }

    public Integer getQuantityBefore() {
        return quantityBefore;
    }

    public void setQuantityBefore(Integer quantityBefore) {
        this.quantityBefore = quantityBefore;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantityAfter() {
        return quantityAfter;
    }

    public void setQuantityAfter(Integer quantityAfter) {
        this.quantityAfter = quantityAfter;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPricePutIn() {
        return pricePutIn;
    }

    public void setPricePutIn(Integer pricePutIn) {
        this.pricePutIn = pricePutIn;
    }

    public Integer getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Integer allPrice) {
        this.allPrice = allPrice;
    }

    public Integer getAllPricePutIn() {
        return allPricePutIn;
    }

    public void setAllPricePutIn(Integer allPricePutIn) {
        this.allPricePutIn = allPricePutIn;
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

    public String getDocterName() {
        return docterName;
    }

    public void setDocterName(String docterName) {
        this.docterName = docterName == null ? null : docterName.trim();
    }

    public String getGentaiName() {
        return gentaiName;
    }

    public void setGentaiName(String gentaiName) {
        this.gentaiName = gentaiName == null ? null : gentaiName.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getZhuyuanNo() {
        return zhuyuanNo;
    }

    public void setZhuyuanNo(String zhuyuanNo) {
        this.zhuyuanNo = zhuyuanNo == null ? null : zhuyuanNo.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc == null ? null : actionDesc.trim();
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