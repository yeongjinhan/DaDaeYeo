package com.hanyj96.dadaeyeo.data.model.products;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class ProductInfo implements Serializable {
    private String productID;
    private String productName;
    private double productPrice;
    private List<String> productImgIDs;
    private String productDesc;
    private int purchaseCount;

    @SuppressWarnings("unused")
    public ProductInfo() {}

    @SuppressWarnings("unused")
    public ProductInfo(String productID, String productName, double productPrice, List<String> productImgIDs, String productDesc, int purchaseCount) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImgIDs = productImgIDs;
        this.productDesc = productDesc;
        this.purchaseCount = purchaseCount;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public List<String> getProductImgIDs() {
        return productImgIDs;
    }

    public void setProductImgIDs(List<String> productImgIDs) {
        this.productImgIDs = productImgIDs;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}

