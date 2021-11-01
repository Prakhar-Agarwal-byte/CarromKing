package com.entwik.carromcash.activity;


import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.entwik.carromcash.R;


public class WithdrawSuccessfulFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.withdrawal_successful, container, false);

        Button keepPlaying = v.findViewById(R.id.keepPlaying);
        keepPlaying.setOnClickListener(v1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new WalletFragment()).commit();
        });
        return v;
    }
}
