package com.entwik.carromcash.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.entwik.carromcash.R;
import com.entwik.carromcash.models.local.LocalDataModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

public class LinkPaytm_Bottomsheet extends BottomSheetDialogFragment {

    private SharedPreferences sp;
    private greenTickPaytmTwo listener;
    private LocalDataModel localDataModel;
    final Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.paytm_add_wallet, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp = view.getContext().getSharedPreferences(getString(R.string.TAG), Context.MODE_PRIVATE);

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

        TextView cancel = view.findViewById(R.id.textView14);
        TextView proceed = view.findViewById(R.id.textView15);
        TextView mobile_num = view.findViewById(R.id.mobile_num);

        mobile_num.setText(localDataModel.getMobileNumber());

        cancel.setOnClickListener(v -> dismiss());

        proceed.setOnClickListener(v -> {
            LinkPaytmOTP_Bottomsheet bottomSheet = new LinkPaytmOTP_Bottomsheet();
            bottomSheet.ShowGreenTickPaytm(() -> listener.showGreenTickPaytmTwo());
            bottomSheet.show(requireActivity().getSupportFragmentManager(), "LinkPaytmOTP");
            dismiss();
        });
    }

    public interface greenTickPaytmTwo{
        void showGreenTickPaytmTwo();
    }

    public void ShowGreenTickPaytmTwo(greenTickPaytmTwo listener){
        this.listener = listener;
    }

}
