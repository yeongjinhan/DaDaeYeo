package com.hanyj96.dadaeyeo.presentation.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavActionBuilder;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHost;
import androidx.navigation.NavOptions;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hanyj96.dadaeyeo.R;
import com.hanyj96.dadaeyeo.databinding.AcitivityMainBinding;
import com.hanyj96.dadaeyeo.presentation.main.home.HomeFragmentDirections;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

/* 메인 액티비티 */
public class MainAcitivity extends DaggerAppCompatActivity implements
        NavController.OnDestinationChangedListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject MainViewModel mainViewModel;
    private AcitivityMainBinding mainBinding;

    // View
    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    /*******************************************
     *  Lifecycle
     *******************************************/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.acitivity_main);
        if(savedInstanceState == null){
            initBottomNavigationView();
            observeCurrentFragment();
        }
    }

    /*******************************************
     *  initViews
     *******************************************/

    private void initBottomNavigationView(){
        // 네비게이션뷰 할당
        bottomNavigationView = mainBinding.mainBottomNavigationView;
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.main_container);
        navController = navHostFragment.getNavController();

        navController.addOnDestinationChangedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // 첫 프래그먼트는 홈 프래그먼트로 지정
        mainViewModel.setCurrentFragment(R.id.homeFragment);
    }

    private void Navigate(int id){
        mainViewModel.setCurrentFragment(id);
        NavOptions navOptions = new NavOptions.Builder().setPopUpTo(id,true).build();
        navController.navigate(id, null, navOptions);
    }

    private void updateNavigationBarState(int id){
        MenuItem menuItem = bottomNavigationView.getMenu().findItem(id);
        if(menuItem != null){
            menuItem.setChecked(true);
        }
    }

    /*******************************************
     *  observeData
     *******************************************/

    private void observeCurrentFragment(){
        mainViewModel.getCurrentFragment().observe(this, this::updateNavigationBarState);
    }

    /*******************************************
     *  Event Listener
     *******************************************/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        updateNavigationBarState(destination.getId());
        Log.d("BackStack","LIST -> " + navController.getBackStack().toString());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Navigate(item.getItemId());
        return true;
    }
}
