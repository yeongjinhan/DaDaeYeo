package com.hanyj96.dadaeyeo.data.model.contents;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class HomeItem implements Serializable {
    private String itemID;
    private int itemType;
    private String itemTitle;
    private List<String> productIDs;

    @SuppressWarnings("unused")
    public HomeItem() {}

    @SuppressWarnings("unused")
    public HomeItem(String itemID, int itemType, String itemTitle, List<String> productIDs) {
        this.itemID = itemID;
        this.itemType = itemType;
        this.itemTitle = itemTitle;
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
