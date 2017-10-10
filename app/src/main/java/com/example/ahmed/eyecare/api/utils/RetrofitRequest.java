package com.example.ahmed.eyecare.api.utils;

import android.util.Log;

import com.example.ahmed.eyecare.api.modelRequest.LoginRequest;
import com.example.ahmed.eyecare.api.modelResponse.LoginResponse;
import com.example.ahmed.eyecare.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 10/10/17.
 */

public class RetrofitRequest {

    private static RetrofitService service = RetrofitConfi.getRetrofit().create(RetrofitService.class);
    private static final String errorMessageForDevelopment = "Error Message For Development";

   public static void login(String email , String code , final RetrofitResponse<User> userRetrofitResponse){
       LoginRequest loginRequest = new LoginRequest.Builder().email(email).code(code).build();
       Call<LoginResponse> response = service.login(loginRequest);
       response.enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               if(response.code()==200){
                   if(response.body().getStatus()){
                       userRetrofitResponse.onSuccess(response.body().getUser());
                   }else {
                       userRetrofitResponse.onFailed(response.body().getMassage());
                   }
               }else {
                   Log.e("onResponse: ",errorMessageForDevelopment );
                   userRetrofitResponse.onFailed(errorMessageForDevelopment);
               }
           }

           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {
               userRetrofitResponse.onFailed(t.getLocalizedMessage());
           }
       });
   }
}
