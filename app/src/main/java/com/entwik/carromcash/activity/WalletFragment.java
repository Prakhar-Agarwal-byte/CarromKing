package com.entwik.carromcash.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.entwik.carromcash.ApiService;
import com.entwik.carromcash.CustomProgressBar;
import com.entwik.carromcash.MyApiEndpointInterface;
import com.entwik.carromcash.R;
import com.entwik.carromcash.models.local.LocalDataModel;
import com.entwik.carromcash.models.wallet.TransactionResponseDataModel;
import com.entwik.carromcash.models.wallet.TransactionResponseModel;
import com.entwik.carromcash.models.wallet.WalletResponseDataModel;
import com.entwik.carromcash.models.wallet.WalletResponseModel;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletFragment extends Fragment {

    SharedPreferences sp;
    WalletResponseDataModel dataModel;
    RecyclerView recyclerView;
    TextView unPlayedAmount, winningAmount, cashBonus, totalBalance;
    private LocalDataModel localDataModel;
    final Gson gson = new Gson();

    CustomProgressBar progressBar;
    List<TransactionResponseDataModel> transdataModel;
    Transaction_List_Adapter adapter;
    List<TransactionResponseDataModel> firstfewtrans;

    String TAG;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wallet, container, false);
        Button withdraw = v.findViewById(R.id.button_withdraw);
        TextView seeAllTransactions = v.findViewById(R.id.seeAllTransactions);
        Button addMoney = v.findViewById(R.id.button_addMoney);
        recyclerView = v.findViewById(R.id.firstfew);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Transaction_List_Adapter();


        TAG = v.getContext().getString(R.string.TAG);

        progressBar = new CustomProgressBar(requireActivity());

        addMoney.setOnClickListener(v12 -> {
            AddCash_Bottomsheet bottomSheet = new AddCash_Bottomsheet();
            bottomSheet.show(requireActivity().getSupportFragmentManager(), "AddMoney");
        });


        withdraw.setOnClickListener(v13 -> {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new WithdrawBalanceFragment()).addToBackStack(null).commit();
                }
        );

        seeAllTransactions.setOnClickListener(v1 -> {
            Bundle bundle = new Bundle();
//            ArrayList<TransactionResponseDataModel> transactions = new ArrayList<>();
            //transactions.add(new TransactionResponseDataModel());
            bundle.putParcelableArrayList("transactions", (ArrayList<? extends Parcelable>) transdataModel);

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, HistoryFragment.class, bundle).addToBackStack(null).commit();
        });


        sp = v.getContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);

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

        unPlayedAmount = v.findViewById(R.id.unplayed_amount);
        winningAmount = v.findViewById(R.id.winning_amount);
        cashBonus = v.findViewById(R.id.cash_bonus);
        totalBalance = v.findViewById(R.id.wallet_balance);

        String unPlayedBalance = localDataModel.getDepositBalance();
        String winningBalance = localDataModel.getWinningBalance();
        String bonusBalance = localDataModel.getBonusBalance();

        unPlayedAmount.setText(unPlayedBalance);
        winningAmount.setText(winningBalance);
        cashBonus.setText(bonusBalance);
        totalBalance.setText(String.valueOf(Integer.parseInt(unPlayedBalance) + Integer.parseInt(winningBalance) + Integer.parseInt(bonusBalance)));

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWalletData();
        getTransactionData();
    }


    private void updateLocal(WalletResponseDataModel wallet) {
        localDataModel.setDepositBalance(String.valueOf(wallet.getDepositBalance()));
        localDataModel.setWinningBalance(String.valueOf(wallet.getWinningBalance()));
        localDataModel.setBonusBalance(String.valueOf(wallet.getBonusBalance()));

        sp.edit().putString("local", new Gson().toJson(localDataModel)).apply();
    }

    private void getTransactionData() {
        ApiService interceptor = new ApiService();
        MyApiEndpointInterface myApiEndpointInterface = interceptor.getApiServiceForInterceptor(interceptor.getInterceptor(sp.getString("token", null)));
        myApiEndpointInterface.getTransactionData().enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().isStatus()) {
                        transdataModel = response.body().getData();
                        firstfewtrans = new ArrayList<>();
                        for (int i = 0; i < transdataModel.size(); i++) {
                            firstfewtrans.add(transdataModel.get(i));
                            if (i == 2) {
                                break;
                            }
                        }
                        adapter.setTasks(firstfewtrans);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }


            @Override
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                Log.d("Failed", t.toString());
            }
        });
    }

    private void getWalletData() {
        progressBar.show();
        ApiService apiService = new ApiService();
        MyApiEndpointInterface apiEndpointInterface = apiService.
                getApiServiceForInterceptor(apiService.getInterceptor(sp.getString("token", null)));


        apiEndpointInterface.getWalletData()
                .enqueue(new Callback<WalletResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<WalletResponseModel> call, @NonNull Response<WalletResponseModel> response) {
                        WalletResponseModel body = response.body();
                        if (body != null) {
                            if (body.isStatus()) {
                                dataModel = body.getData();

                                if (dataModel != null) {
                                    updateLocal(dataModel);
                                    String unPlayedBalance = localDataModel.getDepositBalance();
                                    String winningBalance = localDataModel.getWinningBalance();
                                    String bonusBalance = localDataModel.getBonusBalance();

                                    unPlayedAmount.setText(localDataModel.getDepositBalance());
                                    winningAmount.setText(localDataModel.getWinningBalance());
                                    cashBonus.setText(localDataModel.getBonusBalance());
                                    totalBalance.setText(String.valueOf(Integer.parseInt(unPlayedBalance) + Integer.parseInt(winningBalance) + Integer.parseInt(bonusBalance)));

                                    progressBar.dismiss();
                                } else {
                                    progressBar.dismiss();
                                    Toast.makeText(requireContext(), "Unable to refresh, try again later", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                progressBar.dismiss();
                                Toast.makeText(getContext(), body.getError().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WalletResponseModel> call, @NonNull Throwable t) {
                        progressBar.dismiss();
                    }
                });
    }

}
