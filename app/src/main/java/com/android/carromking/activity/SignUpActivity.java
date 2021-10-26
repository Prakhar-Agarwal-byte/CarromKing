package com.android.carromking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.carromking.ApiService;
import com.android.carromking.CustomProgressBar;
import com.android.carromking.MyApiEndpointInterface;
import com.android.carromking.R;
import com.android.carromking.models.otp.SendOTPResponseDataModel;
import com.android.carromking.models.otp.SendOTPResponseModel;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.tasks.Task;
import com.hbb20.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity{
    
    CountryCodePicker ccp;
    EditText etPhone;
    Button btnGetOTP;

    String TAG;


    private SharedPreferences sp;

    ApiService apiService = new ApiService();
    MyApiEndpointInterface apiEndpointInterface = apiService.getApiService();

    CustomProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        TAG = getString(R.string.TAG);
        progressBar = new CustomProgressBar(this);
        
        ccp = findViewById(R.id.ccp);
        etPhone = findViewById(R.id.etPhone);
        btnGetOTP = findViewById(R.id.btnGetOTP);

        ccp.registerCarrierNumberEditText(etPhone);
        btnGetOTP.setClickable(false);

        sp = getSharedPreferences(TAG, MODE_PRIVATE);

        etPhone.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        Log.d(TAG, "onCreate: " + ccp.isValidFullNumber());
                        if(ccp.isValidFullNumber()) {
                            btnGetOTP.setBackgroundColor(getColor(R.color.blue));
                            btnGetOTP.setClickable(true);
                        } else {
                            btnGetOTP.setClickable(false);
                            btnGetOTP.setBackgroundColor(getColor(R.color.button_grey));
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                }
        );


        btnGetOTP.setOnClickListener(view -> {
            String ccpText = ccp.getSelectedCountryCodeWithPlus();
            String phoneText = etPhone.getText().toString().trim();
            
            if(!ccp.isValidFullNumber()) {
                Toast.makeText(this, "Please add a valid mobile number", Toast.LENGTH_SHORT).show();
            } else {
                progressBar.show();

//                if(!apiService.internetIsConnected()) {
//                    progressBar.hide();
//                    Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
//                } else {
                    apiEndpointInterface.getOtp(phoneText)
                            .enqueue(new Callback<SendOTPResponseModel>() {
                                @Override
                                public void onResponse(@NonNull Call<SendOTPResponseModel> call, @NonNull Response<SendOTPResponseModel> response) {
                                    if(response.body()!=null && response.isSuccessful() && response.body().isStatus()) {
                                        Task<Void> task = SmsRetriever.getClient(SignUpActivity.this).startSmsUserConsent(null);
                                        SendOTPResponseDataModel data = response.body().getData();
                                        sp.edit().putString("mobileNumber", ccpText+ " " +data.getMobileNumber()).apply();
                                        Intent i = new Intent(SignUpActivity.this, EnterOTPActivity.class);
                                        Bundle bundle = new Bundle();
                                        progressBar.dismiss();
                                        bundle.putString("mobileNumber", data.getMobileNumber());
                                        bundle.putString("sessionId", data.getSessionId());
                                        i.putExtras(bundle);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        progressBar.dismiss();
                                        Toast.makeText(SignUpActivity.this, "There was an issue sending the OTP. Please try again after some time", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<SendOTPResponseModel> call, @NonNull Throwable t) {
                                    progressBar.dismiss();
                                }
                            });
//                }
            }
        });
    }
}