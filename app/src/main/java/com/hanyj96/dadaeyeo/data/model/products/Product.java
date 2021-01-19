package com.hanyj96.dadaeyeo.data.model.products;

import java.io.Serializable;

public class Product implements Serializable {
    private int mainCategory;
    private int subCategory;
    private String productID;
    private String productName;
    private double productPrice;

    @SuppressWarnings("unused")
    public Product() {}

    @SuppressWarnings("unused")
    public Product(int mainCategory, int subCategory, String productID, String productName, double productPrice) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(int mainCategory) {
        this.mainCategory = mainCategory;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
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
}
