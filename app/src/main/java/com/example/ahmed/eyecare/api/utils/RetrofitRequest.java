package com.example.ahmed.eyecare.api.utils;

import android.util.Log;

import com.example.ahmed.eyecare.api.modelRequest.ParentRequest;
import com.example.ahmed.eyecare.api.modelResponse.LoginResponse;
import com.example.ahmed.eyecare.api.modelResponse.AllPostsResponse;
import com.example.ahmed.eyecare.api.modelResponse.PostResponse;
import com.example.ahmed.eyecare.model.Post;
import com.example.ahmed.eyecare.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 10/10/17.
 */

public class RetrofitRequest {

    private static RetrofitService service = RetrofitConfi.getRetrofit().create(RetrofitService.class);
    private static final String errorMessageForDevelopment = "Error Message For Development";
    private static final String TAG = "TAG";

   public static void login(String email , String code ,String token, final RetrofitResponse<User> userRetrofitResponse){
       Call<LoginResponse> response = service.login(email,code,token,ParentRequest.getEventId());
       response.enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               if(response.code()==200){
                   if(response.body().getStatus()){
                       userRetrofitResponse.onSuccess(response.body().getUser());
                   }else {
                       userRetrofitResponse.onFailed(response.body().getMassage());
                       Log.i(TAG , response.body().getMassage());
                   }
               }else {
                   Log.e("onResponse: ",errorMessageForDevelopment );
                   userRetrofitResponse.onFailed(errorMessageForDevelopment);
               }
           }

           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {
               userRetrofitResponse.onFailed(t.getLocalizedMessage());
               Log.i(TAG , t.getLocalizedMessage());
           }
       });
   }
   public static void getPosts(int userId,int pageNumber,final RetrofitResponse<List<Post>> listRetrofitResponse){
       Call<AllPostsResponse> response = service.getAllPost(userId,pageNumber,ParentRequest.getEventId());
       response.enqueue(new Callback<AllPostsResponse>() {
           @Override
           public void onResponse(Call<AllPostsResponse> call, Response<AllPostsResponse> response) {
               if(response.code()==200){
                   if(response.body().getStatus()){
                       listRetrofitResponse.onSuccess(response.body().getPosts());
                   }else {
                       listRetrofitResponse.onFailed(response.body().getMassage());
                       Log.e(TAG , response.body().getMassage());
                   }
               }else {
                   Log.e("onResponse: ",errorMessageForDevelopment );
                   listRetrofitResponse.onFailed(errorMessageForDevelopment);
               }
           }

           @Override
           public void onFailure(Call<AllPostsResponse> call, Throwable t) {
               listRetrofitResponse.onFailed(t.getLocalizedMessage());
               Log.e(TAG , t.getLocalizedMessage());
           }
       });
   }
   private static void addPostOrCheckIn(int userId,String post,boolean checkIn,final RetrofitResponse<Post> retrofitResponse){
       Call<PostResponse> response ;

       if(checkIn) // this case make check in so i send 1 as true and empty post
           response =service.addPostOrCheckIn(userId,"",1,ParentRequest.getEventId());
       else // this case make post in so i send 0 as false
           response =service.addPostOrCheckIn(userId,post,0,ParentRequest.getEventId());
       response.enqueue(new Callback<PostResponse>() {
           @Override
           public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
               if(response.code()==200){
                   if(response.body().getStatus()){
                       retrofitResponse.onSuccess(response.body().getPost());
                   }else {
                       retrofitResponse.onFailed(response.body().getMassage());
                       Log.e(TAG , response.body().getMassage());
                   }
               }else {
                   Log.e("onResponse: ",errorMessageForDevelopment );
                   retrofitResponse.onFailed(errorMessageForDevelopment);
               }
           }

           @Override
           public void onFailure(Call<PostResponse> call, Throwable t) {
               retrofitResponse.onFailed(t.getLocalizedMessage());
               Log.e(TAG , t.getLocalizedMessage());
           }
       });
   }
   public static void addPost(int userId,String post,final RetrofitResponse<Post> retrofitResponse){
       addPostOrCheckIn(userId,post,false,retrofitResponse);
   }
    public static void checkIn(int userId,final RetrofitResponse<Post> retrofitResponse){
        addPostOrCheckIn(userId,"",true,retrofitResponse);
    }
}
