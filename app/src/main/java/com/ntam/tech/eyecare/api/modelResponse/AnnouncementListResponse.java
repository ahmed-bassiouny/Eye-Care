package com.ntam.tech.eyecare.api.modelResponse;

import com.ntam.tech.eyecare.model.Announcement;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bassiouny on 19/10/17.
 */

public class AnnouncementListResponse extends ParentResponse{

    @SerializedName("announcement_list")
    private List<Announcement> announcements;

    public List<Announcement> getAnnouncements() {
        if(announcements==null)
            announcements=new ArrayList<>();
        return announcements;
    }
}
