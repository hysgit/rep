package com.woslx.rep.rep.entity.param;

/**
 * Created by hy on 9/3/16.
 */
public class ParamItem {

    private Integer id;

    private Integer typeId;

    private Integer nameId;

    private String serialNumber;

    private String specifications;

    private String company;

    private Integer price;
    private Integer basicPrice;

    public Integer getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(Integer basicPrice) {
        this.basicPrice = basicPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
