package com.example.ahmed.eyecare.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bassiouny on 19/10/17.
 */

public class Photo {

    @SerializedName("id")
    private String id;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;
    @SerializedName("image")
    private String image;
    @SerializedName("image_time")
    private String imageTime;
    @SerializedName("comments")
    private List<Comment> comments;
    @SerializedName("likes")
    private String numberOfLike;
    @SerializedName("make_like")
    private int makeLike;

    public Photo(String id,String imagUrl){
        this.id=id;
        this.image=imagUrl;
    }

    public int getId() {
        try{
            return Integer.parseInt(id);
        }catch (Exception e){
            return 0;
        }
    }

    private String getUserId() {
        return userId;
    }

    public String getImage() {
        return image;
    }

    public String getImageTime() {
        return imageTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    private String getNumberOfLike() {
        return numberOfLike;
    }

    public boolean isLiked() {
        if (makeLike == 1)
            return true;
        return false;
    }
}
