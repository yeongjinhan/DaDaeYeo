package com.hanyj96.dadaeyeo.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends DaggerFragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    protected T dataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        dataBinding = DataBindingUtil.inflate(inflater, getFragmentLayout(), null, false);
        return dataBinding.getRoot();
    }

    protected abstract int getFragmentLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dataBinding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
