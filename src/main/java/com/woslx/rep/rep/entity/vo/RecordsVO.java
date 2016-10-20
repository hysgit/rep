package com.woslx.rep.rep.entity.vo;

/**
 * Created by hy on 9/8/16.
 */
public class RecordsVO {

    private String inOutType;       //出库或入库

    private String itemType;        //商品类型

    private String itemName;        //商品名称

    private String itemSpec;        //商品规格

    private String sn;              //商品编码

    private String inOutDetail;     //进出库详细类型

    private String transn;          //入库的流水号

    private Double before;

    private Double quality;         //出入库的数量

    private Double after;

    private String docName;

    private String gentai;

    private String date;

    private String zhuyuanNo;

    private Integer price;

    private Integer pricePutIn;

    private Integer allPrice;

    private Integer allPricePutIn;

    private String patientName;

    private String srcOrDst;

    public String getSrcOrDst() {
        return srcOrDst;
    }

    public void setSrcOrDst(String srcOrDst) {
        this.srcOrDst = srcOrDst;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPricePutIn() {
        return pricePutIn;
    }

    public void setPricePutIn(Integer pricePutIn) {
        this.pricePutIn = pricePutIn;
    }

    public Integer getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Integer allPrice) {
        this.allPrice = allPrice;
    }

    public Integer getAllPricePutIn() {
        return allPricePutIn;
    }

    public void setAllPricePutIn(Integer allPricePutIn) {
        this.allPricePutIn = allPricePutIn;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getInOutDetail() {
        return inOutDetail;
    }

    public void setInOutDetail(String inOutDetail) {
        this.inOutDetail = inOutDetail;
    }

    public String getZhuyuanNo() {
        return zhuyuanNo;
    }

    public void setZhuyuanNo(String zhuyuanNo) {
        this.zhuyuanNo = zhuyuanNo;
    }

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

    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
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
