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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.entwik.carromcash.R;
import com.entwik.carromcash.models.wallet.TransactionResponseDataModel;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    SharedPreferences sp;
    RecyclerView recyclerView;
    Transaction_List_Adapter adapter;
    ArrayList<TransactionResponseDataModel> transactions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        if(args != null) {
            transactions = args.getParcelableArrayList("transactions");
            Log.d("TAGG", String.valueOf(transactions.size()));
        }

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.wallet_history, container, false);
        sp = v.getContext().getSharedPreferences(getString(R.string.TAG), Context.MODE_PRIVATE);
        recyclerView = v.findViewById(R.id.trans_RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Transaction_List_Adapter();
        adapter.setTasks(transactions);
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("flow is here", "hello im here");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + "History" + "</font>"));
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
}