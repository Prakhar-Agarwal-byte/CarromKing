package com.entwik.carromcash.models.otp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SendOTPResponseDataModel {

    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}