package com.hanyj96.dadaeyeo.presentation.main;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.databinding.AcitivityMainBinding;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

/* 메인 액티비티 */
public class MainAcitivity extends DaggerAppCompatActivity {
    @Inject MainViewModel mainViewModel;
    private AcitivityMainBinding mainBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.acitivity_main);
    }
}
