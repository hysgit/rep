package com.woslx.rep.rep.entity.vo;

import java.util.List;

/**
 * Created by hy on 10/15/16.
 */
public class Opertion {
    private List<OpertionMsg> list;
    private Integer sum;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public List<OpertionMsg> getList() {
        return list;
    }

    public void setList(List<OpertionMsg> list) {
        this.list = list;
    }
}
