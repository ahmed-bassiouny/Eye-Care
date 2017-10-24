package com.ntam.tech.eyecare.api.modelResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bassiouny on 19/10/17.
 */

public class AddPhotoResponse extends ParentResponse {

    @SerializedName("image")
    private String image;
    @SerializedName("photo_id")
    private String photoId;

    public String getImage() {
        return image;
    }

    public String getPhotoId() {
        return photoId;
    }
}
