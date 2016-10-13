package com.woslx.rep.rep.entity.param;

import java.util.Date;

/**
 * Created by hy on 9/7/16.
 */
public class ParamRecordsQueryCondition {
    private Integer id;     //记录id

    private Integer itemTypeId; //商品类型id

    private Integer itemNameId; //商品名称id

    private Integer itemId;     //商品id

    private Integer actionType; //出入库类型

    private Date startTime;      //起始时间

    private String sn;

    //只有结束时间的时候,指比结束时间早的所有
    //只有起始事件的时候,指比起始时间晚的所有
    //起始时间和结束时间都有的时候,指在两者之间的部分
    private Date endTime;       //结束时间

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
