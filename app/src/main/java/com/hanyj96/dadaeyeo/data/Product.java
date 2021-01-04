package com.hanyj96.dadaeyeo.data;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private String ProductID;
    private String ProductName;
    private double ProductPrice;
    private String ProductIMG;
    @SuppressWarnings("unused")
    @ServerTimestamp
    private Date ProductDate;
    @SuppressWarnings("unused")
    public Product() {}

    @SuppressWarnings("unused")
    public Product(String productID, String productName, double productPrice, String productIMG) {
        ProductID = productID;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductIMG = productIMG;
    }

    @Exclude
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductIMG() {
        return ProductIMG;
    }

    public void setProductIMG(String productIMG) {
        ProductIMG = productIMG;
    }

    public String getStringPrice(){
        return Double.toString(ProductPrice);
    }

    @Override
    public boolean equals(@Nullable Object obj){
        return super.equals(obj);
    }
}
