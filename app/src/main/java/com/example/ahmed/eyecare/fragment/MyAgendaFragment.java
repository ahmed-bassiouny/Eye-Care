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
import android.widget.Spinner;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.AgendaDayAdapter;
import com.example.ahmed.eyecare.adapter.SessionMyAgendaAdapter;
import com.example.ahmed.eyecare.model.Agenda;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAgendaFragment extends Fragment {

    List<Agenda> agendaList;
    Spinner spinner;
    RecyclerView recyclerView;
    SessionMyAgendaAdapter sessionMyAgendaAdapter;
    static MyAgendaFragment myAgendaFragment;
    static ArrayList<String> days ;
    AgendaDayAdapter adapterSpinner;
    public MyAgendaFragment() {
        // Required empty public constructor
    }
    public static MyAgendaFragment newInstance(){
        if(myAgendaFragment==null) {
            myAgendaFragment = new MyAgendaFragment();
            days = new ArrayList<>();
        }
        return myAgendaFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_agenda, container, false);
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
        adapterSpinner = new AgendaDayAdapter(getContext(),days);
        spinner.setAdapter(adapterSpinner);
    }
    private void findViewById(View view) {
        spinner=view.findViewById(R.id.spinner);
        recyclerView=view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    private void onClick(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try{

                    if(agendaList==null)
                        getActivity().finish();
                    agendaList.get(position).getSessions();
                    if(sessionMyAgendaAdapter==null)
                        sessionMyAgendaAdapter=new SessionMyAgendaAdapter(getContext());
                    sessionMyAgendaAdapter.setData(agendaList.get(position).getSessions());
                    recyclerView.setAdapter(sessionMyAgendaAdapter);
                }catch (Exception e){
                    getActivity().finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setData(List<Agenda> agendaList){
        this.agendaList=agendaList;
        for(Agenda item:agendaList){
            days.add(item.getDayNumber()+","+item.getEventDate());
        }
    }
}
