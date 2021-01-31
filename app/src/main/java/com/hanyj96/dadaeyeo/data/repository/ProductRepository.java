package com.hanyj96.dadaeyeo.data.repository;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.firebase.firestore.CollectionReference;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.products.userProduct;
import com.hanyj96.dadaeyeo.database.local.ProductDao;
import com.hanyj96.dadaeyeo.database.remote.ProductsDataSourceFactory;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCTS_PAGED_LIST_CONFIG;
import static com.hanyj96.dadaeyeo.utils.Constants.SEARCH_TYPE_CATEGORY;
import static com.hanyj96.dadaeyeo.utils.Constants.SEARCH_TYPE_NAME;
import static com.hanyj96.dadaeyeo.utils.HelperClass.getCurrentDate;
import static com.hanyj96.dadaeyeo.utils.Constants.PRODUCTS_COLLECTION;

@Singleton
public class ProductRepository {
    /* Local */
    private ProductDao productDao;
    /* remote */
    private ProductsDataSourceFactory productsDataSourceFactory;
    private PagedList.Config productsConfig;
    private CollectionReference productsRef;
    /* data */
    private LiveData<PagedList<Product>> productPagedList;
    private LiveData<List<String>> userProductHistoryList;
    private LiveData<List<String>> userProductWishList;

    @Inject
    ProductRepository(ProductDao productDao,
                      @Named(PRODUCTS_PAGED_LIST_CONFIG) PagedList.Config productsConfig,
                      @Named(PRODUCTS_COLLECTION) CollectionReference productsRef){
        this.productDao = productDao;
        this.productsConfig = productsConfig;
        this.productsRef = productsRef;
        // 검색한 제품 기록
        this.userProductHistoryList = productDao.getUserProductHistoryList();
        // 찜한 제품 기록
        this.userProductWishList = productDao.getUserProductWishList();
        // ProductDataSourceFactory Setting
        //this.productsDataSourceFactory = new ProductsDataSourceFactory(SEARCH_TYPE_NAME,null,0,0, productsRef);
        //this.productPagedList = new LivePagedListBuilder<>(productsDataSourceFactory,productsConfig).build();
    }

    /*******************************************
     *  ProductDao
     *******************************************/

    public void insertUserProduct(userProduct userProduct){
        if(userProductHistoryList.getValue().isEmpty()){
            productDao.insert(userProduct);
        }else{
            for(String productId : userProductHistoryList.getValue()){
                if(productId.equals(userProduct.getProductId())){
                    userProduct.setDate(getCurrentDate());
                    productDao.update(userProduct);
                    return;
                }
            }
            productDao.insert(userProduct);
        }
    }

    public void deleteUserProduct(userProduct userProduct){
        productDao.delete(userProduct);
    }

    /*******************************************
     *  ProductsDataSource
     *******************************************/

    public LiveData<PagedList<Product>> getProductsByText(String text){
        if (text == null) {
            productsDataSourceFactory = new ProductsDataSourceFactory(SEARCH_TYPE_NAME,null,0,0, productsRef);
        } else {
            productsDataSourceFactory = new ProductsDataSourceFactory(SEARCH_TYPE_NAME,text,0,0, productsRef);
        }
        return new LivePagedListBuilder<>(productsDataSourceFactory, productsConfig).build();
    }

    public LiveData<PagedList<Product>> getProductsByCategory(int main, int sub){
        productsDataSourceFactory = new ProductsDataSourceFactory(SEARCH_TYPE_CATEGORY, null, main, sub, productsRef);
        return new LivePagedListBuilder<>(productsDataSourceFactory, productsConfig).build();
    }

    /*******************************************
     *  observeData
     *******************************************/

    public LiveData<List<String>> getUserProductHistoryList() {
        return userProductHistoryList;
    }

    public LiveData<List<String>> getUserProductWishList() {
        return userProductWishList;
    }

    //public LiveData<PagedList<Product>> getProductPagedList() { return productPagedList; }
}
