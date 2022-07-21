package com.hanyj96.dadaeyeo.presentation.main.product;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.products.ProductInfo;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;
import javax.inject.Inject;

public class ProductInfoViewModel extends ViewModel {
    private ProductRepository productRepository;
    private LiveData<ProductInfo> productInfo;

    @Inject
    ProductInfoViewModel(ProductRepository productRepository){
        this.productRepository = productRepository;
        productInfo = productRepository.getProductInfo();
    }

    public void loadProductInfoByID(String productID){
        productRepository.getProductInfoByID(productID);
    }

    /*******************************************
     *  observeData
     *******************************************/

    public LiveData<ProductInfo> getProductInfo() {
        return productInfo;
    }
}
