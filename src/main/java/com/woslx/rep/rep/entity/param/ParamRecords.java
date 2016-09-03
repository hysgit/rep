package com.woslx.rep.rep.entity.param;

import java.util.Date;

/**
 * Created by hy on 9/4/16.
 */
public class ParamRecords {
    private Integer id;

    private Integer itemId;

    private Integer actionType;

    private Integer actionDetail;

    private String actionDesc;

    private String srcOrDst;

    private Integer quantity;

    private Date time;

    private String imgUrl;

    private Integer state;

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
        this.actionDesc = actionDesc;
    }

    public String getSrcOrDst() {
        return srcOrDst;
    }

    public void setSrcOrDst(String srcOrDst) {
        this.srcOrDst = srcOrDst;
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
        this.imgUrl = imgUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
