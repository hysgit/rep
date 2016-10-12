package com.woslx.rep.rep.entity.param;

/**
 * Created by hy on 10/11/16.
 */
public class Out {

    private Integer itemId;
    private Integer quality;
    private Integer price;
    private Integer priceAll;
    private String dst;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPriceAll() {
        return priceAll;
    }

    public void setPriceAll(Integer priceAll) {
        this.priceAll = priceAll;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}
