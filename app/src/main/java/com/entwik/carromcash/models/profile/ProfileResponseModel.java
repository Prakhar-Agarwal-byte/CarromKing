package com.entwik.carromcash.models.profile;

import com.entwik.carromcash.models.common.ResponseErrorModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProfileResponseModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private ProfileResponseDataModel data;
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

    public ProfileResponseDataModel getData() {
        return data;
    }

    public void setData(ProfileResponseDataModel data) {
        this.data = data;
    }

}