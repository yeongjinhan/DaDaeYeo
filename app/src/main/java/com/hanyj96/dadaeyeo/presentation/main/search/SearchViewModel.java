package com.hanyj96.dadaeyeo.presentation.main.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.airbnb.lottie.L;
import com.hanyj96.dadaeyeo.data.model.Product;
import com.hanyj96.dadaeyeo.data.repository.ProductRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {
    private ProductRepository productRepository;
    private LiveData<ArrayList<Product>> productLiveData;

    @Inject
    SearchViewModel(ProductRepository productRepository){
        this.productRepository = productRepository;
        productLiveData = productRepository.getProducts();
    }

    public LiveData<ArrayList<Product>> getAll(){
        return productLiveData;
    }
}
