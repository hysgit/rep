package com.woslx.rep.rep.entity.vo;

import java.util.Date;

/**
 * Created by hy on 10/12/16.
 */
public class ItemOut {
    private Integer id;

    private String typeName;

    private String name;

    private String serialNumber;

    private String specifications;

    private Double quantityAll;

    private Double quantityUse;

    private Double quantityCurrent;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Double getQuantityAll() {
        return quantityAll;
    }

    public void setQuantityAll(Double quantityAll) {
        this.quantityAll = quantityAll;
    }

    public Double getQuantityUse() {
        return quantityUse;
    }

    public void setQuantityUse(Double quantityUse) {
        this.quantityUse = quantityUse;
    }

    public Double getQuantityCurrent() {
        return quantityCurrent;
    }

    public void setQuantityCurrent(Double quantityCurrent) {
        this.quantityCurrent = quantityCurrent;
    }
}
