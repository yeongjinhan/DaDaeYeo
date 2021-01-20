package com.hanyj96.dadaeyeo.database.remote;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.hanyj96.dadaeyeo.data.model.products.Product;

public class ProductsDataSourceFactory extends DataSource.Factory<Integer, Product> {
    private MutableLiveData<PageKeyedDataSource<Integer, Product>> productData = new MutableLiveData<>();
    private int searchType;                 // 검색방법 ( 텍스트검색 or 카테고리 검색)
    private int mainCategory;               // 메인 카테고리
    private int subCategory;                // 서브 카테고리
    private String searchText;              // 텍스트로 검색
    private CollectionReference productsRef;

    public ProductsDataSourceFactory(int searchType, String searchText, int mainCategory, int subCategory, CollectionReference productsRef) {
        this.searchType = searchType;
        this.searchText = searchText;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.productsRef = productsRef;
    }

    @NonNull
    @Override
    public DataSource<Integer, Product> create() {
        ProductsDataSource productsDataSource = new ProductsDataSource(searchType,searchText,mainCategory,subCategory,productsRef);
        productData.postValue(productsDataSource);
        return productsDataSource;
    }
}
