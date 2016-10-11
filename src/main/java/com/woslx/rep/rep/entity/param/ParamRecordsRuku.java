package com.woslx.rep.rep.entity.param;

import java.util.Date;
import java.util.List;

/**
 * Created by hy on 9/4/16.
 */
public class ParamRecordsRuku {
    private Integer itemInType;

    private String  transactionalNumber;

    private Date date;

    private List<Ruku> list;

    public Integer getItemInType() {
        return itemInType;
    }

    public void setItemInType(Integer itemInType) {
        this.itemInType = itemInType;
    }

    public String getTransactionalNumber() {
        return transactionalNumber;
    }

    public void setTransactionalNumber(String transactionalNumber) {
        this.transactionalNumber = transactionalNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Ruku> getList() {
        return list;
    }

    public void setList(List<Ruku> list) {
        this.list = list;
    }
}
