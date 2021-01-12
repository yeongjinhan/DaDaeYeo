package com.hanyj96.dadaeyeo.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.hanyj96.dadaeyeo.data.model.Product;
import com.hanyj96.dadaeyeo.datasource.remote.ProductsDataSource;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProductRepository {
    private LiveData<ArrayList<Product>> products;

    @Inject
    ProductRepository(){
        ProductsDataSource productsDataSource = new ProductsDataSource();
        products = productsDataSource.findAll();
    }

    public LiveData<ArrayList<Product>> getProducts(){
        return products;
    }
}
