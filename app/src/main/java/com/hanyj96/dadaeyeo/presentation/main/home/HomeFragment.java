package com.hanyj96.dadaeyeo.presentation.main.home;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.HomeItem;
import com.hanyj96.dadaeyeo.data.model.products.Product;
import com.hanyj96.dadaeyeo.databinding.FragmentHomeBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    @Inject HomeViewModel homeViewModel;
    private HomeVerticalAdapter homeVerticalAdapter;
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    /*******************************************
     *  Lifecycle
     *******************************************/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerAdapter();
        observeHomeItemList();
        observeProductHistoryList();
        observeProductList();
    }

    /*******************************************
     *  initViews
     *******************************************/

    private void initRecyclerAdapter(){
        homeVerticalAdapter = new HomeVerticalAdapter(getContext());
        dataBinding.mainHomeRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.mainHomeRecyclerview.setHasFixedSize(true);
        dataBinding.mainHomeRecyclerview.setAdapter(homeVerticalAdapter);
    }

    /*******************************************
     *  observeDataList
     *******************************************/

    private void observeHomeItemList(){
        homeViewModel.getHomeItemList().
                observe(getViewLifecycleOwner(), homeItems -> {
                    Log.d("홈아이템","홈아이템 업데이트");
                    homeVerticalAdapter.updateItems(homeItems);
                });
    }

    private void observeProductHistoryList(){
        homeViewModel.getProductHistoryList().
                observe(getViewLifecycleOwner(), productID -> {
                    Log.d("제품클릭기록","기록 업데이트");
                    if(!productID.isEmpty()){
                        homeViewModel.searchProductByID(productID);
                    }
                });
    }

    private void observeProductList(){
        homeViewModel.getProductByIdList().observe(getViewLifecycleOwner(), products -> {
            Log.d("제품검색기록","기록 업데이트");
            ArrayList<HomeItem> homeItems = new ArrayList<>();
            homeItems.add(new HomeItem(0,"고객님이 보신 제품들", products));
            homeViewModel.setHomeItemList(homeItems);
        });
    }

}
