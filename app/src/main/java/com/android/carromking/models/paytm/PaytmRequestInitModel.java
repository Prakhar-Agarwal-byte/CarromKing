package com.android.carromking.models.paytm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaytmRequestInitModel {
    @SerializedName("amount")
    @Expose
    String amount;

    public PaytmRequestInitModel(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
