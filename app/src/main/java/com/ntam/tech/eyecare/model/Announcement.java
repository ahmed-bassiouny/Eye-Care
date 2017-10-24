package com.ntam.tech.eyecare.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bassiouny on 19/10/17.
 */

public class Announcement {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public int getId() {
        try{
            return Integer.parseInt(id);
        }catch (Exception e){
            return 0;
        }
    }

    public String getName() {
        return name;
    }
}

