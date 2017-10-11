package com.example.ahmed.eyecare.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmed on 11/10/17.
 */

public class Post {


    @SerializedName("id")
    private String postId;

    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;

    @SerializedName("userImage")
    private String avatar;
    @SerializedName("post")
    private String post;

    @SerializedName("post_date")
    private String postDate;

    @SerializedName("make_like")
    private int makeLike; // is liked
    @SerializedName("comments")
    private List<Comment> comments;
    @SerializedName("likes")
    private String numberOfLike;

    public String getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPost() {
        return post;
    }

    public String getPostDate() {
        return postDate;
    }

    public boolean getIsMakeLike() {
        if (makeLike == 1)
            return true;
        return false;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getNumberOfLike() {
        return numberOfLike;
    }
}
