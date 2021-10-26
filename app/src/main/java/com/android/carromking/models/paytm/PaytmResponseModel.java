package com.android.carromking.models.paytm;

import com.android.carromking.models.common.ResponseErrorModel;
import com.android.carromking.models.wallet.WalletResponseDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaytmResponseModel {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private PaytmResponseDataModel data;
    @SerializedName("error")
    @Expose
    private ResponseErrorModel error;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public PaytmResponseDataModel getData() {
        return data;
    }

    public void setData(PaytmResponseDataModel data) {
        this.data = data;
    }

    public ResponseErrorModel getError() {
        return error;
    }

    public void setError(ResponseErrorModel error) {
        this.error = error;
    }
}
