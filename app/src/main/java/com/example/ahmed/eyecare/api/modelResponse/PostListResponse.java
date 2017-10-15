package com.example.ahmed.eyecare.api.modelResponse;

import com.example.ahmed.eyecare.model.Post;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmed on 11/10/17.
 */

public class PostListResponse extends ParentResponse {
    @SerializedName("all_posts")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }
}
