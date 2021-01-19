package com.hanyj96.dadaeyeo.data.model.products;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class userProduct {
    @PrimaryKey(autoGenerate = true)
    private int upId;
    private int type;
    private String productId;
    private String date;

    public userProduct(int type, String productId, String date) {
        this.type = type;
        this.productId = productId;
        this.date = date;
    }

    public int getUpId() {
        return upId;
    }

    public void setUpId(int upId) {
        this.upId = upId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
