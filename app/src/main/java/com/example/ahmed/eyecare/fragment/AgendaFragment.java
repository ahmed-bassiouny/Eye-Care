package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.SessionAgendaAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Agenda;
import com.example.ahmed.eyecare.utils.DummyData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgendaFragment extends Fragment {

    List<Agenda> agendaList;
    Spinner spinner;
    RecyclerView recyclerView;
    SessionAgendaAdapter sessionAgendaAdapter;
    static AgendaFragment agendaFragment;
    ArrayAdapter<String> adapterSpinner;
    static ArrayList<String> days ;


    public AgendaFragment() {
        // Required empty public constructor
    }
    public static AgendaFragment newInstance(){
        if(agendaFragment==null) {
            agendaFragment = new AgendaFragment();
            days = new ArrayList<>();
        }
        return agendaFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agenda, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(view);
        onClick();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterSpinner = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, days);
        spinner.setAdapter(adapterSpinner);
    }

    private void findViewById(View view) {
        spinner=view.findViewById(R.id.spinner);
        recyclerView=view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }


    public void setData(List<Agenda> agendaList){
        this.agendaList=agendaList;
        for(Agenda item:agendaList){
            days.add(item.getDayNumber()+" "+item.getEventDate());
        }
    }
    private void onClick(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{
                    if(agendaList==null)
                        getActivity().finish();
                    agendaList.get(position).getSessions();
                    if(sessionAgendaAdapter==null)
                        sessionAgendaAdapter=new SessionAgendaAdapter(getContext());
                    sessionAgendaAdapter.setData(agendaList.get(position).getSessions());
                    recyclerView.setAdapter(sessionAgendaAdapter);
                }catch (Exception e){
                    getActivity().finish();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}