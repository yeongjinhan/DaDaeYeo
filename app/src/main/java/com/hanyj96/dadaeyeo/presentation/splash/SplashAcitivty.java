package com.hanyj96.dadaeyeo.presentation.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hanyj96.dadaeyeo.presentation.main.MainAcitivity;

import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;
import static com.hanyj96.dadaeyeo.utils.ActivityEvent.ChangeActivity;

/* 스플래시 액티비티 */
public class SplashAcitivty extends DaggerAppCompatActivity {
    @Inject SplashViewModel splashViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeActivity(this, new Intent(this, MainAcitivity.class));
    }
}
