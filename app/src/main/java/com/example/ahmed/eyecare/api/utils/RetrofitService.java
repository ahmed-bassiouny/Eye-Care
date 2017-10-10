package com.example.ahmed.eyecare.api.utils;

import com.example.ahmed.eyecare.api.modelRequest.LoginRequest;
import com.example.ahmed.eyecare.api.modelResponse.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ahmed on 10/10/17.
 */

public interface RetrofitService {
     String LOGIN= "login.php";


    @POST(LOGIN)
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
