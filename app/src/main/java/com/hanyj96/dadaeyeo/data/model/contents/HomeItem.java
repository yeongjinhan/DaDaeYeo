package com.hanyj96.dadaeyeo.data.model.contents;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class HomeItem implements Serializable {
    private String itemID;
    private int itemType;
    private String itemTitle;
    private String productCategory;
    private String productSubCategory;
    private List<String> productIDs;

    @SuppressWarnings("unused")
    public HomeItem() {}

    @SuppressWarnings("unused")
    public HomeItem(String itemID, int itemType, String itemTitle,String productCategory, String productSubCategory, List<String> productIDs) {
        this.itemID = itemID;
        this.itemType = itemType;
        this.itemTitle = itemTitle;
        this.productCategory = productCategory;
        this.productSubCategory = productSubCategory;
        this.productIDs = productIDs;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public List<String> getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(List<String> productIDs) {
        this.productIDs = productIDs;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
