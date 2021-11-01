package com.entwik.carromcash.models.wallet;

import com.entwik.carromcash.models.common.ResponseErrorModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionResponseModel {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private List<TransactionResponseDataModel> data;
    @SerializedName("error")
    @Expose
    private ResponseErrorModel error;

    public ResponseErrorModel getError() {
        return error;
    }

    public void setError(ResponseErrorModel error) {
        this.error = error;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<TransactionResponseDataModel> getData() {
        return data;
    }

    public void setData(List<TransactionResponseDataModel> data) {
        this.data = data;
    }
}
