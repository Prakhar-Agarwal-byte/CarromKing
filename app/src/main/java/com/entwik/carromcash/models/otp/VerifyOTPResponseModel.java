package com.entwik.carromcash.models.otp;

import com.entwik.carromcash.models.common.ResponseErrorModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOTPResponseModel {


    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private ResponseErrorModel error;
    @SerializedName("data")
    @Expose
    private VerifyOTPResponseDataModel data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ResponseErrorModel getError() {
        return error;
    }

    public void setError(ResponseErrorModel error) {
        this.error = error;
    }

    public VerifyOTPResponseDataModel getData() {
        return data;
    }

    public void setData(VerifyOTPResponseDataModel data) {
        this.data = data;
    }
}
