package com.hanyj96.dadaeyeo.presentation.main.home;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.BaseApplication;
import com.hanyj96.dadaeyeo.data.HomeItem;
import com.hanyj96.dadaeyeo.data.Product;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    private ArrayList<HomeItem> homeItems;
    @Inject
    public HomeViewModel(){
        if(homeItems == null){
            homeItems = new ArrayList<>();
            ArrayList<Product> products = new ArrayList<>();
            products.add(new Product("1","아이폰",100000,"null"));
            products.add(new Product("2","갤럭시",100000,"null"));
            products.add(new Product("3","LG",100000,"null"));
            homeItems.add(new HomeItem(1,"새로운상품보기",products));
        }
    }
    public ArrayList<HomeItem> getHomeItems(){
        return homeItems;
    }
}
