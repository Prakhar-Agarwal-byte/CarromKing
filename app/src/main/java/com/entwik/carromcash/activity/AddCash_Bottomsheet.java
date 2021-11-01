package com.entwik.carromcash.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.entwik.carromcash.R;;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddCash_Bottomsheet extends BottomSheetDialogFragment {
    Button button10, button20, button50, button100;
    EditText amountEditText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_cash, container, false);
        button10 = v.findViewById(R.id.button4);
        button20 = v.findViewById(R.id.button5);
        button50 = v.findViewById(R.id.button6);
        button100 = v.findViewById(R.id.button7);
        amountEditText= v.findViewById(R.id.editTextNumber4);
        button10.setOnClickListener(v1 -> {
            amountEditText.setText(v.getContext().getString(R.string.rupee) + "10");
            button10.setBackgroundResource(R.drawable.custom_button);
            button20.setBackgroundResource(R.drawable.custom_button2);
            button50.setBackgroundResource(R.drawable.custom_button2);
            button100.setBackgroundResource(R.drawable.custom_button2);

            button10.setTextColor(Color.parseColor("#FFFFFF"));
            button20.setTextColor(Color.parseColor("#000000"));
            button50.setTextColor(Color.parseColor("#000000"));
            button100.setTextColor(Color.parseColor("#000000"));

        });
        button20.setOnClickListener(v12 -> {
            amountEditText.setText(v.getContext().getString(R.string.rupee) + "20");
            button20.setBackgroundResource(R.drawable.custom_button);
            button10.setBackgroundResource(R.drawable.custom_button2);
            button50.setBackgroundResource(R.drawable.custom_button2);
            button100.setBackgroundResource(R.drawable.custom_button2);

            button20.setTextColor(Color.parseColor("#FFFFFF"));
            button10.setTextColor(Color.parseColor("#000000"));
            button50.setTextColor(Color.parseColor("#000000"));
            button100.setTextColor(Color.parseColor("#000000"));
        });
        button50.setOnClickListener(v13 -> {
            amountEditText.setText(v.getContext().getString(R.string.rupee) + "50");
            button50.setBackgroundResource(R.drawable.custom_button);
            button20.setBackgroundResource(R.drawable.custom_button2);
            button10.setBackgroundResource(R.drawable.custom_button2);
            button100.setBackgroundResource(R.drawable.custom_button2);

            button50.setTextColor(Color.parseColor("#FFFFFF"));
            button20.setTextColor(Color.parseColor("#000000"));
            button10.setTextColor(Color.parseColor("#000000"));
            button100.setTextColor(Color.parseColor("#000000"));
        });
        button100.setOnClickListener(v14 -> {
            amountEditText.setText(v.getContext().getString(R.string.rupee) + "100");
            button100.setBackgroundResource(R.drawable.custom_button);
            button20.setBackgroundResource(R.drawable.custom_button2);
            button50.setBackgroundResource(R.drawable.custom_button2);
            button10.setBackgroundResource(R.drawable.custom_button2);

            button100.setTextColor(Color.parseColor("#FFFFFF"));
            button20.setTextColor(Color.parseColor("#000000"));
            button50.setTextColor(Color.parseColor("#000000"));
            button10.setTextColor(Color.parseColor("#000000"));
        });

        amountEditText.setText(v.getContext().getString(R.string.rupee));

        Selection.setSelection(amountEditText.getText(), amountEditText.getText().length());


        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!s.toString().startsWith(v.getContext().getString(R.string.rupee))){
                    amountEditText.setText(v.getContext().getString(R.string.rupee));
                    Selection.setSelection(amountEditText.getText(), amountEditText.getText().length());

                }

                if(amountEditText.getText().toString().trim().equals(v.getContext().getString(R.string.rupee) + "10")) {
                    button10.setBackgroundResource(R.drawable.custom_button);
                    button20.setBackgroundResource(R.drawable.custom_button2);
                    button50.setBackgroundResource(R.drawable.custom_button2);
                    button100.setBackgroundResource(R.drawable.custom_button2);

                    button10.setTextColor(Color.parseColor("#FFFFFF"));
                    button20.setTextColor(Color.parseColor("#000000"));
                    button50.setTextColor(Color.parseColor("#000000"));
                    button100.setTextColor(Color.parseColor("#000000"));
                } else if(amountEditText.getText().toString().trim().equals(v.getContext().getString(R.string.rupee) + "20")){

                    button20.setBackgroundResource(R.drawable.custom_button);
                    button10.setBackgroundResource(R.drawable.custom_button2);
                    button50.setBackgroundResource(R.drawable.custom_button2);
                    button100.setBackgroundResource(R.drawable.custom_button2);

                    button20.setTextColor(Color.parseColor("#FFFFFF"));
                    button10.setTextColor(Color.parseColor("#000000"));
                    button50.setTextColor(Color.parseColor("#000000"));
                    button100.setTextColor(Color.parseColor("#000000"));
                }else if(amountEditText.getText().toString().trim().equals(v.getContext().getString(R.string.rupee) + "50")){
                    button50.setBackgroundResource(R.drawable.custom_button);
                    button20.setBackgroundResource(R.drawable.custom_button2);
                    button10.setBackgroundResource(R.drawable.custom_button2);
                    button100.setBackgroundResource(R.drawable.custom_button2);

                    button50.setTextColor(Color.parseColor("#FFFFFF"));
                    button20.setTextColor(Color.parseColor("#000000"));
                    button10.setTextColor(Color.parseColor("#000000"));
                    button100.setTextColor(Color.parseColor("#000000"));
                }else if(amountEditText.getText().toString().trim().equals(v.getContext().getString(R.string.rupee) + "100")){
                    button100.setBackgroundResource(R.drawable.custom_button);
                    button20.setBackgroundResource(R.drawable.custom_button2);
                    button50.setBackgroundResource(R.drawable.custom_button2);
                    button10.setBackgroundResource(R.drawable.custom_button2);

                    button100.setTextColor(Color.parseColor("#FFFFFF"));
                    button20.setTextColor(Color.parseColor("#000000"));
                    button50.setTextColor(Color.parseColor("#000000"));
                    button10.setTextColor(Color.parseColor("#000000"));
                }else{
                    button10.setBackgroundResource(R.drawable.custom_button2);
                    button20.setBackgroundResource(R.drawable.custom_button2);
                    button50.setBackgroundResource(R.drawable.custom_button2);
                    button100.setBackgroundResource(R.drawable.custom_button2);

                    button10.setTextColor(Color.parseColor("#000000"));
                    button20.setTextColor(Color.parseColor("#000000"));
                    button50.setTextColor(Color.parseColor("#000000"));
                    button100.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
        return v;
    }
}
