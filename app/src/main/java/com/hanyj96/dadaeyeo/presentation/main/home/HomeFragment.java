package com.hanyj96.dadaeyeo.presentation.main.home;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.databinding.FragmentHomeBinding;
import com.hanyj96.dadaeyeo.presentation.BaseFragment;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    @Inject HomeViewModel homeViewModel;
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

}