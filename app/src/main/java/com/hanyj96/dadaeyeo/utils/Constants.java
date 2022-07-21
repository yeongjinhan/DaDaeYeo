package com.hanyj96.dadaeyeo.utils;

public interface Constants {
    int HOME_ITEM_TYPE1 = 1;

    /*******************************************
     *  Firebase
     *******************************************/
    int SEARCH_TYPE_ALL = 0;
    int SEARCH_TYPE_NAME = 1;
    int SEARCH_TYPE_CATEGORY = 2;
    int PRODUCTS_PER_PAGE = 3;

    String PRODUCTS_PAGED_LIST_CONFIG = "ProductPagedConfig";
    String ESCAPE_CHARACTER = "\uf8ff";
    String PRODUCTS_COLLECTION = "Products";
    String PRODUCTS_NAME_FILED = "productName";

    String HOME_ITEMS_PAGED_LIST_CONFIG = "HomePagedConfig";
    int HOME_ITEMS_PER_PAGE = 3;
    String HOME_ITEMS_COLLECTION = "HomeItems";
    String HOME_ITEMS_FILED = "itemID";

    /*******************************************
     *  Products
     *******************************************/

    int PRODUCT_HISTORY = 0;
    int PRODUCT_WISH_LIST = 1;

    /*******************************************
     *  Util
     *******************************************/
    String BASE_TRACK = "baseTrack";
    String TRACKER = "tracker";
}
