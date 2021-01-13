package com.hanyj96.dadaeyeo.presentation.main.search;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.data.model.HomeItem;
import com.hanyj96.dadaeyeo.data.model.Product;
import com.hanyj96.dadaeyeo.databinding.FragmentSearchBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;
import com.hanyj96.dadaeyeo.presentation.main.OnProductClickListener;
import com.hanyj96.dadaeyeo.presentation.main.home.HomeVerticalAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> implements Observer<ArrayList<Product>>, OnProductClickListener {
    @Inject SearchViewModel searchViewModel;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadProducts();
        initEditText();
    }

    private void initEditText(){
        // 검색 프래그먼트가 호출되면 자동으로 검색창에 포커스 주입
        dataBinding.editTextInputWord.requestFocus();
        // 포커스 주입과 동시에 키보드 호출
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void loadProducts(){
        searchViewModel.getAll().observe(getViewLifecycleOwner(),this);
    }
    @Override
    public void onProductClick(Product product) {
        Log.d("Product",product.getProductName());
    }

    @Override
    public void onChanged(ArrayList<Product> products) {
        if(products != null){
            SearchRecyclerAdapter searchRecyclerAdapter = new SearchRecyclerAdapter(products, this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(dataBinding.mainSearchRecyclerview.getContext(),2);
            dataBinding.mainSearchRecyclerview.setLayoutManager(gridLayoutManager);
            dataBinding.mainSearchRecyclerview.setHasFixedSize(true);
            dataBinding.mainSearchRecyclerview.setAdapter(searchRecyclerAdapter);
        }
    }
}
