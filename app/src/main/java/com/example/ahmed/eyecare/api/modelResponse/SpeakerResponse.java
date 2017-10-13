package com.example.ahmed.eyecare.api.modelResponse;

import com.example.ahmed.eyecare.model.Speaker;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 14/10/17.
 */

public class SpeakerResponse extends  ParentResponse  {
    @SerializedName("all_speaker")
    private List<Speaker> speakers;

    public List<Speaker> getSpeakers() {
        if(speakers==null)
            speakers= new ArrayList<>();
        return speakers;
    }
}
