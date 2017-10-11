package com.example.ahmed.eyecare.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 11/10/17.
 */

class Comment {

    @SerializedName("id")
    private String commentId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;
    @SerializedName("comment")
    private String comment;
    @SerializedName("comment_date")
    private String commentDate;
    @SerializedName("user_image")
    private String userAvatar;


    public String getCommentId() {
        return commentId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public String getUserAvatar() {
        return userAvatar;
    }
}
