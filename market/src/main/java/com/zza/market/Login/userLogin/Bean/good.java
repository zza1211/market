package com.zza.market.Login.userLogin.Bean;

import java.math.BigDecimal;

public class good {
    private int id;
    private String name;
    private BigDecimal price;
    private String describe;
    private String imageurl;
    public good(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", describe='" + describe + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }}
