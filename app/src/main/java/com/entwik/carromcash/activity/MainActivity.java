package com.entwik.carromcash.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.entwik.carromcash.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFF"));
        assert getSupportActionBar() != null;
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().hide();

        bottomNav = findViewById(R.id.bottom_navigation);
//      this is to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment(bottomNav)).commit();
        }


        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;

            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new HomeFragment(bottomNav);

            } else if (item.getItemId() == R.id.nav_wallet) {
                selectedFragment = new WalletFragment();
            } else {
                selectedFragment = new ProfileFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

            return true;
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(getString(R.string.TAG), "onOptionsItemSelected: Hello");
        if(item.getItemId() == android.R.id.home) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(bottomNav));
    }
}