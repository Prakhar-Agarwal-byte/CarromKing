package com.entwik.carromcash.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.entwik.carromcash.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class EnterUPI_Bottomsheet extends BottomSheetDialogFragment {

    private greenTickUpi listener;
    EditText etUpiId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.enter_upi_id, container, false);
        Button verifyAndProceedUpi = v.findViewById(R.id.verifyAndProceedUpi);
        etUpiId = v.findViewById(R.id.etUpiId);

        verifyAndProceedUpi.setClickable(false);
        verifyAndProceedUpi.setBackgroundColor(v.getContext().getColor(R.color.button_grey));

        etUpiId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String s = etUpiId.getText().toString().trim();
                if(!s.contains("@")  || s.length() < 6) {
                    verifyAndProceedUpi.setClickable(false);
                    verifyAndProceedUpi.setBackgroundColor(v.getContext().getColor(R.color.button_grey));
                } else {
                    verifyAndProceedUpi.setClickable(true);
                    verifyAndProceedUpi.setBackgroundColor(v.getContext().getColor(R.color.blue));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        verifyAndProceedUpi.setOnClickListener(v1 -> {
            listener.showGreenTickUpi();
            dismiss();
        });

        return v;
    }

    public interface greenTickUpi{
        void showGreenTickUpi();
    }

    public void ShowGreenTickUpi(greenTickUpi listener){
        this.listener = listener;
    }
}
