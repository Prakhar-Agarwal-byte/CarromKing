package com.entwik.carromcash.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.entwik.carromcash.ApiService;
import com.entwik.carromcash.CustomProgressBar;
import com.entwik.carromcash.MyApiEndpointInterface;
import com.entwik.carromcash.R;
import com.entwik.carromcash.models.common.UserDataModel;
import com.entwik.carromcash.models.common.UserWalletDataModel;
import com.entwik.carromcash.models.local.LocalDataModel;
import com.entwik.carromcash.models.profile.LeagueModel;
import com.entwik.carromcash.models.profile.ProfileResponseDataModel;
import com.entwik.carromcash.models.profile.ProfileResponseModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    SharedPreferences sp;
    ProfileResponseDataModel dataModel;
    String TAG;

    TextView tvMobileNumber, tvAmountBalance;
    ImageView profile_lock_diamond, profile_lock_gold, profile_lock_silver;
    TextView profile_status_gold, profile_status_diamond, profile_status_silver, profile_user_league_name;
    Button btnAddMoney;

    ImageView profileImage, trophyImage;

    LocalDataModel localDataModel;

    CustomProgressBar progressBar;

    final Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        progressBar = new CustomProgressBar(getActivity());

        TAG = getString(R.string.TAG);

        sp = view.getContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        Log.d(TAG, "onViewCreated: " + sp.getString("token", null));

        LocalDataModel localDataModel1 = new LocalDataModel(
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

        tvMobileNumber = view.findViewById(R.id.profile_phone_num);
        tvAmountBalance = view.findViewById(R.id.tvBalance);
        profile_lock_diamond = view.findViewById(R.id.profile_lock_diamond);
        profile_lock_gold = view.findViewById(R.id.profile_lock_gold);
        profile_lock_silver = view.findViewById(R.id.profile_lock_silver);
        profile_status_gold = view.findViewById(R.id.profile_status_gold);
        profile_status_diamond = view.findViewById(R.id.profile_status_diamond);
        profile_status_silver = view.findViewById(R.id.profile_status_silver);
        profile_user_league_name = view.findViewById(R.id.profile_user_league_name);

        trophyImage = view.findViewById(R.id.profile_user_league_img);
        profileImage = view.findViewById(R.id.profile_profile_icon);

        btnAddMoney = view.findViewById(R.id.btnAddMoney);

        trophyImage.setRotation(12);
        getLevel(localDataModel.getLevel(), view.getContext());

        tvMobileNumber.setText(localDataModel.getMobileNumber());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getProfileData();

        btnAddMoney.setOnClickListener(view1 -> {
            AddCash_Bottomsheet bottomSheet = new AddCash_Bottomsheet();
            bottomSheet.show(requireActivity().getSupportFragmentManager(), "AddMoney");
        });
    }

    private void getLevel(String level, Context context) {
        if (level != null) {
            switch (level) {
                case "diamond":
                    trophyImage.setBackground(ContextCompat.getDrawable(context, R.drawable.diamond_trophy_tilted));
                    profile_user_league_name.setText(requireContext().getString(R.string.diamond_lea));
                    break;
                case "gold":
                    trophyImage.setBackground(ContextCompat.getDrawable(context, R.drawable.golden_trophy_tilted));
                    profile_user_league_name.setText(requireContext().getString(R.string.gold_lea));
                    break;
                case "silver":
                    trophyImage.setBackground(ContextCompat.getDrawable(context, R.drawable.silver_trophy_tilted));
                    profile_user_league_name.setText(requireContext().getString(R.string.silver_lea));
                    break;
            }
        }

    }

    private void updateLocal(UserDataModel user, UserWalletDataModel wallet) {
        localDataModel.setDepositBalance(String.valueOf(wallet.getDepositBalance()));
        localDataModel.setWinningBalance(String.valueOf(wallet.getWinningBalance()));
        localDataModel.setBonusBalance(String.valueOf(wallet.getBonusBalance()));
        localDataModel.setLevel(user.getLevel());
        localDataModel.setProfilePic(user.getProfilePic());

        sp.edit().putString("local", new Gson().toJson(localDataModel)).apply();
    }

    @Override
    public void onPause() {
        dataModel = null;
        super.onPause();
    }

    private void getProfileData() {

        progressBar.show();
        ApiService apiService = new ApiService();
        MyApiEndpointInterface apiEndpointInterface = apiService.getApiServiceForInterceptor(apiService.getInterceptor(sp.getString("token", null)));

//        if(!apiService.internetIsConnected()) {
//            progressBar.hide();
//            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show();
//        } else {
            apiEndpointInterface.getProfileData()
                    .enqueue(new Callback<ProfileResponseModel>() {
                        @Override
                        public void onResponse(@NonNull Call<ProfileResponseModel> call, @NonNull Response<ProfileResponseModel> response) {
                            if (response.body() != null) {
                                if (response.body().isStatus()) {
                                    dataModel = response.body().getData();
                                    Log.d(TAG, "onResponse: Profile " + dataModel.getUserData().getUsername());

                                /*
                                {dataModel.getUserData().getMobileNumber()}
                                and
                                {dataModel.getUserData().getToken()}
                                is null here
                                Don't use it for this api
                                 */

                                    if (dataModel != null) {
                                        //Connect UI Here
                                        UserDataModel user = dataModel.getUserData();
                                        List<LeagueModel> leagues = dataModel.getLeagues();
                                        tvAmountBalance.setText(String.valueOf(dataModel.getWalletData().getDepositBalance()));

                                        updateLocal(user, dataModel.getWalletData());

                                        leagues.forEach(league -> {
                                            switch (league.getName()) {
                                                case "diamond":
                                                    profile_lock_diamond.setVisibility(league.isIsLocked() ? View.VISIBLE : View.GONE);
                                                    profile_status_diamond.setText(league.getDescription());
                                                    break;
                                                case "gold":
                                                    profile_lock_gold.setVisibility(league.isIsLocked() ? View.VISIBLE : View.GONE);
                                                    profile_status_gold.setText(league.getDescription());
                                                    break;
                                                case "silver":
                                                    profile_lock_silver.setVisibility(league.isIsLocked() ? View.VISIBLE : View.GONE);
                                                    profile_status_silver.setText(league.getDescription());
                                                    break;
                                            }
                                        });
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
                        public void onFailure(@NonNull Call<ProfileResponseModel> call, @NonNull Throwable t) {
                            progressBar.dismiss();
                        }
                    });
//        }
    }
}
