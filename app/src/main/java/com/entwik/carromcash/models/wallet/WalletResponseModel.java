package com.entwik.carromcash.models.wallet;

import com.entwik.carromcash.models.common.ResponseErrorModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletResponseModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private WalletResponseDataModel data;
    @SerializedName("error")
    @Expose
    private ResponseErrorModel error;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public WalletResponseDataModel getData() {
        return data;
    }

    public void setData(WalletResponseDataModel data) {
        this.data = data;
    }

    public ResponseErrorModel getError() {
        return error;
    }

    public void setError(ResponseErrorModel error) {
        this.error = error;
    }
}
