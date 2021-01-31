package com.hanyj96.dadaeyeo.presentation.main.productlist;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;

import javax.inject.Inject;

public class ProductListViewModel extends ViewModel {
    private ProductRepository productRepository;
    private LiveData<PagedList<Product>> productList;

    @Inject
    ProductListViewModel(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void loadProductsListByCategory(int main, int sub){
        productList = productRepository.getProductsByCategory(main, sub);
    }

    /*******************************************
     *  observeData
     *******************************************/

    public LiveData<PagedList<Product>> getProductList() {
        return productList;
    }
}
