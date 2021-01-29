package com.hanyj96.dadaeyeo.data.repository;

import androidx.lifecycle.LiveData;
import com.hanyj96.dadaeyeo.data.model.products.userProduct;
import com.hanyj96.dadaeyeo.database.local.ProductDao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import static com.hanyj96.dadaeyeo.utils.HelperClass.getCurrentDate;

@Singleton
public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<String>> userProductHistoryList;
    private LiveData<List<String>> userProductWishList;

    @Inject
    ProductRepository(ProductDao productDao){
        this.productDao = productDao;
        // 검색한 제품 기록
        this.userProductHistoryList = productDao.getUserProductHistoryList();
        // 찜한 제품 기록
        this.userProductWishList = productDao.getUserProductWishList();
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
     *  observeData
     *******************************************/

    public LiveData<List<String>> getUserProductHistoryList() {
        return userProductHistoryList;
    }

    public LiveData<List<String>> getUserProductWishList() {
        return userProductWishList;
    }

}
