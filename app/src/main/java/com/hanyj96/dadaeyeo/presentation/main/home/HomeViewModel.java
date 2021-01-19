package com.hanyj96.dadaeyeo.presentation.main.home;

import android.widget.LinearLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hanyj96.dadaeyeo.data.model.HomeItem;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<HomeItem>> homeItemList = new MutableLiveData<>();
    private LiveData<List<String>> productHistoryList;
    private LiveData<ArrayList<Product>> productByIdList;
    private ProductRepository productRepository;

    @Inject
    public HomeViewModel(ProductRepository productRepository){
        this.productRepository = productRepository;
        productHistoryList = productRepository.getUserProductHistoryList();
        productByIdList = productRepository.getUserProductList();
    }

    /*******************************************
     *  ProductRepository
     *******************************************/

    public void searchProductByID(List<String> productID){
        productRepository.searchProductByID(productID);
    }

    /*******************************************
     *  observeData
     *******************************************/

    public MutableLiveData<ArrayList<HomeItem>> getHomeItemList() {
        return homeItemList;
    }

    public void setHomeItemList(ArrayList<HomeItem> homeItemList){
        this.homeItemList.setValue(homeItemList);
    }

    public LiveData<List<String>> getProductHistoryList() {
        return productHistoryList;
    }

    public LiveData<ArrayList<Product>> getProductByIdList() {
        return productByIdList;
    }
}
