package com.hanyj96.dadaeyeo.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends DaggerFragment {
    private static final String TAG = "BaseFragment";
    protected T dataBinding;
    protected T temp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        dataBinding = DataBindingUtil.inflate(inflater, getFragmentLayout(), null, false);
        Log.d(TAG, "onCreateView()");
        if(bundle != null){
            Log.d(TAG, "저장된 데이터 존재함");
        }

        if(temp != null){
            Log.d(TAG,temp.toString());
            return temp.getRoot();
        }
        return dataBinding.getRoot();
    }

    protected abstract int getFragmentLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView() -> " + dataBinding.getRoot().getId());
        //temp = dataBinding;
        dataBinding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
