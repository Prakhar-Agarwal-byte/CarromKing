package com.entwik.carromcash.models.otp;

public class VerifyOTPBodyModel {

    String mobileNumber;
    String sessionId;
    String otp;

    public VerifyOTPBodyModel(String mobileNumber, String sessionId, String otp) {
        this.mobileNumber = mobileNumber;
        this.sessionId = sessionId;
        this.otp = otp;
    }

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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
