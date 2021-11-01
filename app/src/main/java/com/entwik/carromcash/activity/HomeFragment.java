package com.entwik.carromcash.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.entwik.carromcash.ApiService;
import com.entwik.carromcash.CustomProgressBar;
import com.entwik.carromcash.MyApiEndpointInterface;
import com.entwik.carromcash.R;
import com.entwik.carromcash.models.home.HomeResponseDataModel;
import com.entwik.carromcash.models.home.HomeResponseModel;
import com.entwik.carromcash.models.local.LeagueList;
import com.entwik.carromcash.models.local.LocalDataModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    SharedPreferences sp;
    HomeResponseDataModel dataModel;
    TextView homeWallet;
    LocalDataModel localDataModel;

    LinearLayout llWallet;

    CustomProgressBar progressBar;
    final Gson gson = new Gson();

    final BottomNavigationView bottomNav;

    SwipeRefreshLayout swipeRefreshLayout;

    String[] tab_array = {"Beginner", "Silver", "Gold", "Diamond"};

    HomeFragment(BottomNavigationView bottomNav) {
        this.bottomNav = bottomNav;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container,false);

        homeWallet = v.findViewById(R.id.home_wallet);
        llWallet = v.findViewById(R.id.llWallet);
        ViewPager2 viewPager = v.findViewById(R.id.viewPager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this);
        viewPager.setAdapter(categoryAdapter);
        TabLayout tabLayout = v.findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position)-> tab.setText(tab_array[position])).attach();

        progressBar = new CustomProgressBar(getActivity());

        swipeRefreshLayout = v.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            getHomeData();
            swipeRefreshLayout.setRefreshing(false);
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                swipeRefreshLayout.setEnabled(state == ViewPager2.SCROLL_STATE_IDLE);
            }
        });



        llWallet.setOnClickListener(view -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new WalletFragment()).commit();
            bottomNav.setSelectedItemId(R.id.nav_wallet);
        });

        sp = v.getContext().getSharedPreferences(getString(R.string.TAG), Context.MODE_PRIVATE);

        LocalDataModel localDataModel1 =  new LocalDataModel(
                "1",
                getString(R.string.mobile_number),
                "",
                "silver",
                sp.getString("token", null),
                "0",
                "0",
                "0"
        );
        localDataModel = gson.fromJson(sp.getString("local", gson.toJson(localDataModel1)), LocalDataModel.class);
        String unPlayedBalance = localDataModel.getDepositBalance();
        String winningBalance = localDataModel.getWinningBalance();
        String bonusBalance = localDataModel.getBonusBalance();

        homeWallet.setText(String.valueOf(Integer.parseInt(unPlayedBalance) + Integer.parseInt(winningBalance) + Integer.parseInt(bonusBalance)));

        return v;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getHomeData();

    }

    private void getHomeData() {

        progressBar.show();

        ApiService interceptor = new ApiService();
        MyApiEndpointInterface apiEndpointInterface = interceptor.getApiServiceForInterceptor(interceptor.getInterceptor(sp.getString("token", null)));

//        if(!interceptor.internetIsConnected()) {
//            progressBar.hide();
//            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show();
//        } else {
            apiEndpointInterface.getHomeData()
                    .enqueue(new Callback<HomeResponseModel>() {
                        @Override
                        public void onResponse(@NonNull Call<HomeResponseModel> call, @NonNull Response<HomeResponseModel> response) {
                            if (response.body() != null) {
                                if (response.body().isStatus()) {
                                    dataModel = response.body().getData().get(0);
                                    if (dataModel != null) {
                                        LeagueList.getInstance().setLobbies(dataModel.getLobbies());
                                        progressBar.dismiss();
                                    } else {
                                        progressBar.dismiss();
                                        Toast.makeText(requireContext(), "Unable to refresh, try again later", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    progressBar.dismiss();
                                    Toast.makeText(getContext(), response.body().getError().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<HomeResponseModel> call, @NonNull Throwable t) {
                            progressBar.dismiss();
                        }
                    });

    }


}
