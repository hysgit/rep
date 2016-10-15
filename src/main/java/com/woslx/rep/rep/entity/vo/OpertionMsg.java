package com.woslx.rep.rep.entity.vo;

import java.util.List;

/**
 * Created by hy on 10/15/16.
 */
public class OpertionMsg {

    private String docName;

    private String gentai;

    private String date;

    private String zhuyuanNo;

    private String patientName;

    private Integer total;  //总价

    private List<RecordsVO> recordsVOList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getZhuyuanNo() {
        return zhuyuanNo;
    }

    public void setZhuyuanNo(String zhuyuanNo) {
        this.zhuyuanNo = zhuyuanNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getGentai() {
        return gentai;
    }

    public void setGentai(String gentai) {
        this.gentai = gentai;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<RecordsVO> getRecordsVOList() {
        return recordsVOList;
    }

    public void setRecordsVOList(List<RecordsVO> recordsVOList) {
        this.recordsVOList = recordsVOList;
    }
}
