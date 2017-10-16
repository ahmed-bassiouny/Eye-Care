package com.example.ahmed.eyecare.api.modelResponse;

import com.example.ahmed.eyecare.model.Agenda;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bassiouny on 16/10/17.
 */

public class AgendaListResponse extends ParentResponse {

    @SerializedName("agenda")
    private List<Agenda> agendaList;

    public List<Agenda> getAgendaList() {
        if(agendaList==null)
            agendaList=new ArrayList<>();
        return agendaList;
    }
}
