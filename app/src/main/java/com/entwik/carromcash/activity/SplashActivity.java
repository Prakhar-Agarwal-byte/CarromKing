package com.entwik.carromcash.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.entwik.carromcash.R;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences sp;

    final String TAG = "com.android.carromking";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
        sp = getSharedPreferences(TAG, MODE_PRIVATE);

        new Handler().postDelayed(() -> {
            Intent i = sp.getString("token", null) != null ?
                    new Intent(SplashActivity.this, MainActivity.class) :
                    new Intent(SplashActivity.this, SignUpActivity.class);
            startActivity(i);
            finish();
        }, 3000);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), findViewById(R.id.root_layout));
        controller.hide(WindowInsetsCompat.Type.systemBars());
        controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
    }
}