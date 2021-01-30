package com.hanyj96.dadaeyeo.presentation.main.productlist;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.databinding.FragmentProductListBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;

public class ProductListFragment extends BaseFragment<FragmentProductListBinding> {

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_product_list;
    }

    /*******************************************
     *  Lifecycle
     *******************************************/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
