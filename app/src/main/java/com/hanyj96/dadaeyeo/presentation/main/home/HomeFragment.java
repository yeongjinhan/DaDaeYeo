package com.hanyj96.dadaeyeo.presentation.main.home;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.HomeItem;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerAdapter();
        loadHomeItemData();
        Log.d("홈뷰모델","홈 프레그먼트 생성");
    }

    private void initRecyclerAdapter(){
        Log.d("HomeFragment","Init RecyclerView Adapter");
        //homeViewModel.getHomeItemLiveData().observe(getViewLifecycleOwner(),HomeItemUpdateObserver);
    }

    /*Observer<ArrayList<HomeItem>> HomeItemUpdateObserver = new Observer<ArrayList<HomeItem>>() {
        @Override
        public void onChanged(ArrayList<HomeItem> homeItems) {
            homeVerticalAdapter = new HomeVerticalAdapter(dataBinding.mainHomeRecyclerview.getContext(), homeItems);
            dataBinding.mainHomeRecyclerview.setAdapter(homeVerticalAdapter);
        }
    };*/

    private void loadHomeItemData(){

    }
}
