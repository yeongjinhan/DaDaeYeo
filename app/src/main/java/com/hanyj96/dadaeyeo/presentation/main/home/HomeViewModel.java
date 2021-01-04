package com.hanyj96.dadaeyeo.presentation.main.home;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.data.HomeItem;
import com.hanyj96.dadaeyeo.data.Product;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    private HomeVerticalAdapter homeVerticalAdapter;
    private ArrayList<HomeItem> homeItems;
    @Inject
    public HomeViewModel(){
        if(homeItems == null){
            homeItems = new ArrayList<>();
            ArrayList<Product> products = new ArrayList<>();
            Product product = new Product("1","아이폰",100000,"null");
            products.add(product);
            homeItems.add(new HomeItem(1,"새로운상품보기",products));
        }
        if(homeVerticalAdapter == null){
            homeVerticalAdapter = new HomeVerticalAdapter(homeItems);
        }
    }

    public HomeVerticalAdapter getAdapter(){
        return homeVerticalAdapter;
    }
}
