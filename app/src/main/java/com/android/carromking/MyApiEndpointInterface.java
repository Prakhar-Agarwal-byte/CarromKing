package com.android.carromking;

import com.android.carromking.models.home.HomeResponseModel;
import com.android.carromking.models.otp.SendOTPResponseModel;
import com.android.carromking.models.otp.VerifyOTPBodyModel;
import com.android.carromking.models.otp.VerifyOTPResponseModel;
import com.android.carromking.models.paytm.PaytmRequestInitModel;
import com.android.carromking.models.paytm.PaytmResponseModel;
import com.android.carromking.models.profile.ProfileResponseModel;
import com.android.carromking.models.wallet.TransactionResponseModel;
import com.android.carromking.models.wallet.WalletResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApiEndpointInterface {
    // Request method and URL specified in the annotation

    @GET("auth/otp")
    Call<SendOTPResponseModel> getOtp(@Query("mobileNumber") String mobileNumber);

    @POST("auth")
    Call<VerifyOTPResponseModel> verifyOTP(@Body VerifyOTPBodyModel bodyModel);

    @GET("user/profile")
    Call<ProfileResponseModel> getProfileData();

    @GET("home/games")
    Call<HomeResponseModel> getHomeData();

    @GET("user/wallet")
    Call<WalletResponseModel> getWalletData();

    @GET("user/transaction")
    Call<TransactionResponseModel> getTransactionData();

    @POST("user/transaction/deposit/init")
    Call<PaytmResponseModel> initPaytmTxn(@Body PaytmRequestInitModel amount);

}
