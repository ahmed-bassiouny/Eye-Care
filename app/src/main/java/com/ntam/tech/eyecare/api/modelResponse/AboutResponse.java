package com.ntam.tech.eyecare.api.modelResponse;

import com.ntam.tech.eyecare.model.About;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bassiouny on 18/10/17.
 */

public class AboutResponse extends ParentResponse {
    @SerializedName("about_event")
    private About about;

    public About getAbout() {
        if(about==null)
            about=new About();
        return about;
    }
}
