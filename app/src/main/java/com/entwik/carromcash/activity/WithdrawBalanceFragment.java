package com.entwik.carromcash.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.entwik.carromcash.R;
import com.entwik.carromcash.models.local.LocalDataModel;
import com.google.gson.Gson;

public class WithdrawBalanceFragment extends Fragment {

    final Gson gson = new Gson();
    SharedPreferences sp;
    String TAG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_withdraw_balance, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TAG = getString(R.string.TAG);
        sp = view.getContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);

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

        LocalDataModel localDataModel = gson.fromJson(sp.getString("local", gson.toJson(localDataModel1)), LocalDataModel.class);


        Button withdrawNow = view.findViewById(R.id.withdraw_now_button);
        TextView linkPaytm = view.findViewById(R.id.link_account_paytm);
        LinearLayout linkUPI = view.findViewById(R.id.arrow_upi);
        LinearLayout linkBank = view.findViewById(R.id.arrow_bank_transfer);
        LinearLayout greenTickAndArrowPaytm = view.findViewById(R.id.greenTickAndArrowPaytm);
        ImageView greenTickUpi = view.findViewById(R.id.greenTickUpi);
        ImageView greenTickBank = view.findViewById(R.id.greenTickBankTransfer);
        TextView withdrawableBalance = view.findViewById(R.id.withdrawble_balance_amount);
        EditText etAmountWithdraw = view.findViewById(R.id.amount_to_withdraw);

        withdrawableBalance.setText( "₹ "+ localDataModel.getWinningBalance());

        withdrawNow.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new WithdrawSuccessfulFragment()).commit());

        linkPaytm.setOnClickListener(v -> {
            LinkPaytm_Bottomsheet bottomSheet = new LinkPaytm_Bottomsheet();
            bottomSheet.ShowGreenTickPaytmTwo(() -> {
                greenTickAndArrowPaytm.setVisibility(View.VISIBLE);
                linkPaytm.setVisibility(View.GONE);
                greenTickBank.setVisibility(View.GONE);
                greenTickUpi.setVisibility(View.GONE);
            });
            bottomSheet.show(requireActivity().getSupportFragmentManager(), "LinkPaytm");
        });

        linkUPI.setOnClickListener(v -> {
            EnterUPI_Bottomsheet bottomSheet = new EnterUPI_Bottomsheet();
            bottomSheet.ShowGreenTickUpi(() -> {
                greenTickUpi.setVisibility(View.VISIBLE);
                greenTickBank.setVisibility(View.GONE);
                greenTickAndArrowPaytm.setVisibility(View.GONE);
                linkPaytm.setVisibility(View.VISIBLE);
            });
            bottomSheet.show(requireActivity().getSupportFragmentManager(), "LinkUPI");
        });

        linkBank.setOnClickListener(v -> {
            LinkBank_Bottomsheet bottomSheet = new LinkBank_Bottomsheet();
            bottomSheet.ShowGreenTickBank(() -> {
                greenTickBank.setVisibility(View.VISIBLE);
                greenTickUpi.setVisibility(View.GONE);
                linkPaytm.setVisibility(View.VISIBLE);
                greenTickAndArrowPaytm.setVisibility(View.GONE);
            });
            bottomSheet.show(requireActivity().getSupportFragmentManager(), "LinkBank");
        });

        Selection.setSelection(etAmountWithdraw.getText(), etAmountWithdraw.getText().length());

        etAmountWithdraw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith(view.getContext().getString(R.string.rupee))){
                    etAmountWithdraw.setText(view.getContext().getString(R.string.rupee));
                    Selection.setSelection(etAmountWithdraw.getText(), etAmountWithdraw.getText().length());

                }
            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)requireActivity()).getSupportActionBar().show();
        ((AppCompatActivity)requireActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + "Withdraw Balance" + "</font>"));
        ((AppCompatActivity)requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)requireActivity()).getSupportActionBar().setElevation(0);
        ((AppCompatActivity)requireActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        @SuppressLint("UseCompatLoadingForDrawables")
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_keyboard_backspace_24, requireContext().getTheme());
        ((AppCompatActivity)requireActivity()).getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)requireActivity()).getSupportActionBar().hide();
    }

}
