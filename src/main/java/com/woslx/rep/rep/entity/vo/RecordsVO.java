package com.woslx.rep.rep.entity.vo;

/**
 * Created by hy on 9/8/16.
 */
public class RecordsVO {

    private String inOutType;

    private String transn;

    private Double before;

    private Double now;

    private Double after;

    private String docName;

    private String gentai;

    private String date;

    public String getInOutType() {
        return inOutType;
    }

    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    public String getTransn() {
        return transn;
    }

    public void setTransn(String transn) {
        this.transn = transn;
    }

    public Double getBefore() {
        return before;
    }

    public void setBefore(Double before) {
        this.before = before;
    }

    public Double getNow() {
        return now;
    }

    public void setNow(Double now) {
        this.now = now;
    }

    public Double getAfter() {
        return after;
    }

    public void setAfter(Double after) {
        this.after = after;
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
}
