package com.hanyj96.dadaeyeo.data;

import java.util.ArrayList;

public class HomeItem {
    private int ItemType;
    private String Title;
    private ArrayList<Product> Products;

    public HomeItem(int itemType, String title, ArrayList<Product> products) {
        ItemType = itemType;
        Title = title;
        Products = products;
    }

    public int getItemType() {
        return ItemType;
    }

    public void setItemType(int itemType) {
        ItemType = itemType;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public ArrayList<Product> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<Product> products) {
        Products = products;
    }
}
