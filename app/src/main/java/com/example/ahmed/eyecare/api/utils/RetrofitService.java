package com.example.ahmed.eyecare.api.utils;

import com.example.ahmed.eyecare.api.modelRequest.LoginRequest;
import com.example.ahmed.eyecare.api.modelRequest.ParentRequest;
import com.example.ahmed.eyecare.api.modelRequest.PostRequest;
import com.example.ahmed.eyecare.api.modelResponse.LoginResponse;
import com.example.ahmed.eyecare.api.modelResponse.PostsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ahmed on 10/10/17.
 */

public interface RetrofitService {
     String LOGIN= "login.php";
    String ALL_POST = "all_posts.php";

    @FormUrlEncoded
    @POST(LOGIN)
    Call<LoginResponse> login(@Field(LoginRequest.EMAIL_KEY) String email,
                              @Field(LoginRequest.CODE_KEY) String code,
                              @Field(LoginRequest.TOKEN_KEY) String token,
                              @Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(ALL_POST)
    Call<PostsResponse> getAllPost(@Field(PostRequest.USER_ID_KEY) int userId,
                                   @Field(PostRequest.PAGE_NUMBER_KEY) int pageNumber,
                                   @Field(ParentRequest.EVENT_KEY) int event_id);
}
