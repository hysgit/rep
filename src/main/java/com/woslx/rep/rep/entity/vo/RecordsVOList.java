package com.woslx.rep.rep.entity.vo;

import com.woslx.rep.rep.entity.Records;

import java.util.List;

/**
 * Created by hy on 9/8/16.
 */
public class RecordsVOList {

    private Integer cnt;

    private List<Records> list;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<Records> getList() {
        return list;
    }

    public void setList(List<Records> list) {
        this.list = list;
    }
}
