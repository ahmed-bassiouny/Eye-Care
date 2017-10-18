package com.example.ahmed.eyecare.model;

import com.example.ahmed.eyecare.utils.Utils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ahmed on 11/10/17.
 */

public class Post implements Serializable {


    @SerializedName("id")
    private String postId;

    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;

    @SerializedName("user_image")
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

    public int getPostId() {
        try{
            return Integer.parseInt(postId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
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

    public void setIsMakeLike(boolean like) {
        if (like)
            makeLike = 1;
        else
            makeLike = 0;
    }

    public String getDayDate() {
        return String.valueOf(Utils.getCalender(postDate).get(Calendar.DAY_OF_MONTH));
    }

    public String getTime() {
        return String.valueOf(Utils.getCalender(postDate).get(Calendar.HOUR) + ":" + Utils.getCalender(postDate).get(Calendar.MINUTE));
    }

    public String getMonth() {
        return String.valueOf(Utils.getMothStringByNumber(Utils.getCalender(postDate).get(Calendar.MONTH)));
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getNumberOfLike() {
        return numberOfLike;
    }
    public boolean increaseNumberOfLike(){
        try{
            int temp;
            temp =Integer.parseInt(numberOfLike);
            temp++;
            numberOfLike=String.valueOf(temp);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getCommentsSize() {
        return String.valueOf(comments.size());
    }

}
