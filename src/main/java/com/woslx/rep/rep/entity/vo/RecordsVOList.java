package com.woslx.rep.rep.entity.vo;

import com.woslx.rep.rep.entity.Records;

import java.util.List;

/**
 * Created by hy on 9/8/16.
 */
public class RecordsVOList {

    private Integer id;

    private String typeName;

    private String itemName;

    private String sn;

    private String spec;

    private Double allIn;

    private Double allOut;

    private Double cur;

    private List<RecordsVO> list;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Double getAllIn() {
        return allIn;
    }

    public void setAllIn(Double allIn) {
        this.allIn = allIn;
    }

    public Double getAllOut() {
        return allOut;
    }

    public void setAllOut(Double allOut) {
        this.allOut = allOut;
    }

    public Double getCur() {
        return cur;
    }

    public void setCur(Double cur) {
        this.cur = cur;
    }

    public List<RecordsVO> getList() {
        return list;
    }

    public void setList(List<RecordsVO> list) {
        this.list = list;
    }
}
