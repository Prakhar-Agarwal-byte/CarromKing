package com.android.carromking.models.paytm;

import com.android.carromking.models.common.ResponseErrorModel;
import com.android.carromking.models.wallet.WalletResponseDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaytmResultBodyModel {
    @SerializedName("resultStatus")
    @Expose
    private String resultStatus;
    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("resultMsg")
    @Expose
    private String resultMsg;

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
