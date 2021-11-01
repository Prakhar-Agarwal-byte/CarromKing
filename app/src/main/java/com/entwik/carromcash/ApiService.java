package com.entwik.carromcash;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    //Used for Calling API where Authorization token is required

    private static final String BASE_URL = "https://ecommerce-checkout.herokuapp.com/";


    public OkHttpClient getInterceptor(String token) {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", token)
                            .build();
                    return chain.proceed(newRequest);
                }).build();
    }

    public MyApiEndpointInterface getApiServiceForInterceptor(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(MyApiEndpointInterface.class);
    }

    public  MyApiEndpointInterface getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MyApiEndpointInterface.class);
    }

    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }
}
