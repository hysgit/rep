package com.woslx.rep.rep.entity;

import java.util.Date;

/**
 * Created by hy on 9/8/16.
 */
public class RecordsQueryCondition {
    private Integer id;     //记录id

    private Integer itemTypeId; //商品类型id

    private Integer itemNameId; //商品名称id

    private Integer itemId;     //商品id

    private Integer actionType; //出入库类型

    private String startTime;      //起始时间

    private String endTime;       //结束时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Integer getItemNameId() {
        return itemNameId;
    }

    public void setItemNameId(Integer itemNameId) {
        this.itemNameId = itemNameId;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
