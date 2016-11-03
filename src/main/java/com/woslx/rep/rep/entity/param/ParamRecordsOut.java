package com.woslx.rep.rep.entity.param;

import java.util.Date;
import java.util.List;

/**
 * Created by hy on 9/4/16.
 */
public class ParamRecordsOut {
    private Integer itemOutType;

    private String  docterName;
    private String  gentaiName;
    private String  patientName;
    private String  zhuyuanNo;
    private String dailishang;

    private Date date;

    private List<Out> list;

    public String getDailishang() {
        return dailishang;
    }

    public void setDailishang(String dailishang) {
        this.dailishang = dailishang;
    }

    public Integer getItemOutType() {
        return itemOutType;
    }

    public void setItemOutType(Integer itemOutType) {
        this.itemOutType = itemOutType;
    }


    public String getDocterName() {
        return docterName;
    }

    public void setDocterName(String docterName) {
        this.docterName = docterName;
    }

    public String getGentaiName() {
        return gentaiName;
    }

    public void setGentaiName(String gentaiName) {
        this.gentaiName = gentaiName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getZhuyuanNo() {
        return zhuyuanNo;
    }

    public void setZhuyuanNo(String zhuyuanNo) {
        this.zhuyuanNo = zhuyuanNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Out> getList() {
        return list;
    }

    public void setList(List<Out> list) {
        this.list = list;
    }
}
