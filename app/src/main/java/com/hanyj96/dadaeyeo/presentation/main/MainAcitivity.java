package com.hanyj96.dadaeyeo.presentation.main;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.databinding.AcitivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

/* 메인 액티비티 */
public class MainAcitivity extends DaggerAppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{
    @Inject MainViewModel mainViewModel;
    private AcitivityMainBinding mainBinding;

    // View
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.acitivity_main);
        if(savedInstanceState == null){
            initBottomNavigationView();
            //mainViewModel.InitData();
            //mainViewModel.testData();
        }
    }
    private void initBottomNavigationView(){
        // 네비게이션뷰 할당
        bottomNavigationView = mainBinding.mainBottomNavigationView;
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.main_container);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottom_menu_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()){
            case R.id.bottom_menu_cataegory:
                navController.navigate(R.id.cataegoryFragment);
                return true;
            case R.id.bottom_menu_search:
                navController.navigate(R.id.searchFragment);
                return true;
            case R.id.bottom_menu_home:
                navController.navigate(R.id.homeFragment);
                return true;
            case R.id.bottom_menu_myinfo:
                navController.navigate(R.id.myinfoFragment);
                return true;
            case R.id.bottom_menu_myrent:
                navController.navigate(R.id.myrentFragment);
                return true;
        }
        return false;
    }
}
