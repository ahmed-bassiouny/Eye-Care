package com.example.ahmed.eyecare.api.utils;

import com.example.ahmed.eyecare.api.modelRequest.LoginRequest;
import com.example.ahmed.eyecare.api.modelRequest.ParentRequest;
import com.example.ahmed.eyecare.api.modelRequest.AllPostRequest;
import com.example.ahmed.eyecare.api.modelRequest.PostRequest;
import com.example.ahmed.eyecare.api.modelResponse.LoginResponse;
import com.example.ahmed.eyecare.api.modelResponse.AllPostsResponse;
import com.example.ahmed.eyecare.api.modelResponse.PostResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ahmed on 10/10/17.
 */

public interface RetrofitService {
    String LOGIN = "login.php";
    String ALL_POST = "all_posts.php";
    String ADD_POST_CHECKIN = "add_post.php";

    @FormUrlEncoded
    @POST(LOGIN)
    Call<LoginResponse> login(@Field(LoginRequest.EMAIL_KEY) String email,
                              @Field(LoginRequest.CODE_KEY) String code,
                              @Field(LoginRequest.TOKEN_KEY) String token,
                              @Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(ALL_POST)
    Call<AllPostsResponse> getAllPost(@Field(AllPostRequest.USER_ID_KEY) int userId,
                                      @Field(AllPostRequest.PAGE_NUMBER_KEY) int pageNumber,
                                      @Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(ADD_POST_CHECKIN)
    Call<PostResponse> addPostOrCheckIn(@Field(PostRequest.USER_ID_KEY) int userId,
                                  @Field(PostRequest.POST_KEY) String post,
                                  @Field(PostRequest.CHECK_IN_KEY) int checkIn,
                                  @Field(ParentRequest.EVENT_KEY) int event_id);


}
