package com.example.ahmed.eyecare.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 15/10/17.
 */

public class Attendee {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("position")
    private String position;
    @SerializedName("company")
    private String company;

    public int getId() {
        try{
            return Integer.parseInt(id);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPosition() {
        return position;
    }

    public String getCompany() {
        return company;
    }
}
