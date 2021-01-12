package com.hanyj96.dadaeyeo.presentation.main.search;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.HomeItem;
import com.hanyj96.dadaeyeo.data.model.Product;
import com.hanyj96.dadaeyeo.databinding.FragmentSearchBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;
import com.hanyj96.dadaeyeo.presentation.main.home.HomeVerticalAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    @Inject SearchViewModel searchViewModel;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("SearchFragment","Fragment Create");
        searchViewModel.getAll().observe(getViewLifecycleOwner(),ProductItemUpdateObserver);
    }

    Observer<ArrayList<Product>> ProductItemUpdateObserver = new Observer<ArrayList<Product>>() {
        @Override
        public void onChanged(ArrayList<Product> products) {
            if(products != null){
                SearchRecyclerAdapter searchRecyclerAdapter = new SearchRecyclerAdapter(products);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(dataBinding.mainSearchRecyclerview.getContext(),2);
                dataBinding.mainSearchRecyclerview.setLayoutManager(gridLayoutManager);
                dataBinding.mainSearchRecyclerview.setHasFixedSize(true);
                dataBinding.mainSearchRecyclerview.setAdapter(searchRecyclerAdapter);
            }
        }
    };
}
