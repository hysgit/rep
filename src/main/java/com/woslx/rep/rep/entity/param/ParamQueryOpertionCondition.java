package com.woslx.rep.rep.entity.param;

import java.util.Date;
import java.util.List;

/**
 * Created by hy on 9/7/16.
 */
public class ParamQueryOpertionCondition {

    private Integer queryType;      //查询类型

    private List<String> docNameList;

    private List<String> gentaiList;

    private List<Integer> typeList;

    private String zhuyuanNO;

    private Date start;

    private Date end;

    public List<Integer> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public List<String> getDocNameList() {
        return docNameList;
    }

    public void setDocNameList(List<String> docNameList) {
        this.docNameList = docNameList;
    }

    public List<String> getGentaiList() {
        return gentaiList;
    }

    public void setGentaiList(List<String> gentaiList) {
        this.gentaiList = gentaiList;
    }

    public String getZhuyuanNO() {
        return zhuyuanNO;
    }

    public void setZhuyuanNO(String zhuyuanNO) {
        this.zhuyuanNO = zhuyuanNO;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
