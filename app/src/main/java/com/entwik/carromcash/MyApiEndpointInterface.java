package com.entwik.carromcash;

import com.entwik.carromcash.models.home.HomeResponseModel;
import com.entwik.carromcash.models.otp.SendOTPResponseModel;
import com.entwik.carromcash.models.otp.VerifyOTPBodyModel;
import com.entwik.carromcash.models.otp.VerifyOTPResponseModel;
import com.entwik.carromcash.models.profile.ProfileResponseModel;
import com.entwik.carromcash.models.wallet.TransactionResponseModel;
import com.entwik.carromcash.models.wallet.WalletResponseModel;

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

}
