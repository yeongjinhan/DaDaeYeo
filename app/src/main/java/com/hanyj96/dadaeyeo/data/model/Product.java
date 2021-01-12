package com.hanyj96.dadaeyeo.data.model;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class Product implements Serializable {
    private String ProductID;
    private String ProductName;
    private double ProductPrice;

    @SuppressWarnings("unused")
    public Product() {}

    @SuppressWarnings("unused")
    public Product(String productID, String productName, double productPrice) {
        ProductID = productID;
        ProductName = productName;
        ProductPrice = productPrice;
    }

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

    @Override
    public boolean equals(@Nullable Object obj){
        return super.equals(obj);
    }
}
