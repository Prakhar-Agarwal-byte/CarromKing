package com.entwik.carromcash;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.entwik.carromcash.R;

import java.util.Objects;

public class CustomProgressBar extends Dialog {

    public CustomProgressBar( Activity act) {
        super(act);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window;
//        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.progress_bar);

        setCancelable(false);


        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }
}
