package com.example.ahmed.eyecare.model;

import com.example.ahmed.eyecare.utils.Utils;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
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



    public boolean getIsMakeLike() {
        if (makeLike == 1)
            return true;
        return false;
    }
    public String getDayDate(){
        return String.valueOf(Utils.getCalender(postDate).get(Calendar.DAY_OF_MONTH));
    }
    public String getTime(){
        return String.valueOf(Utils.getCalender(postDate).get(Calendar.HOUR)+":"+Utils.getCalender(postDate).get(Calendar.MINUTE));
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getNumberOfLike() {
        return numberOfLike;
    }

    public String getCommentsSize(){
        return String.valueOf(comments.size());
    }

}
