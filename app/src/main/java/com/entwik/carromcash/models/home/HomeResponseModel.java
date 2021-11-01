package com.entwik.carromcash.models.home;

import com.entwik.carromcash.models.common.ResponseErrorModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponseModel {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private List<HomeResponseDataModel> data = null;
    @SerializedName("error")
    @Expose
    private ResponseErrorModel error;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<HomeResponseDataModel> getData() {
        return data;
    }

    public void setData(List<HomeResponseDataModel> data) {
        this.data = data;
    }

    public ResponseErrorModel getError() {
        return error;
    }

    public void setError(ResponseErrorModel error) {
        this.error = error;
    }
}
