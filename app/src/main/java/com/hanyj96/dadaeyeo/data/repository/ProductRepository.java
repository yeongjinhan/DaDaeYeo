package com.hanyj96.dadaeyeo.data.repository;

import androidx.lifecycle.LiveData;

import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.database.remote.ProductsDataSource;

import java.security.Key;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProductRepository {
    private LiveData<ArrayList<Product>> products;
    private LiveData<ArrayList<Keyword>> autoKeywords;
    private ProductsDataSource productsDataSource;

    @Inject
    ProductRepository(){
        productsDataSource = new ProductsDataSource();
        products = productsDataSource.findAll();
        autoKeywords = productsDataSource.findAutoKeywords();
    }

    public LiveData<ArrayList<Keyword>> getAutoKeywords(){
        return autoKeywords;
    }

    public LiveData<ArrayList<Product>> getProducts(){
        return products;
    }

    public void searchProductforName(String word){
        productsDataSource.SearchProduct(word);
    }

    public void searchProductforKeyword(String word) {
        productsDataSource.getAutoKeywords(word);
    }
}
