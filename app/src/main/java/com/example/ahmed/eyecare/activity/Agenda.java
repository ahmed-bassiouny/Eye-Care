package com.example.ahmed.eyecare.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.fragment.AgendaFragment;
import com.example.ahmed.eyecare.utils.Utils;

public class Agenda extends AppCompatActivity {

    private TextView tvAgena;
    private TextView tvMyAgena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        findViewById();
        onClick();
        Utils.goToFragment(this,AgendaFragment.newInstance(),null,null);
    }

    private void onClick() {
        tvAgena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAgena.setTextColor(ContextCompat.getColor(Agenda.this, R.color.header));
                tvAgena.setBackground(ContextCompat.getDrawable(Agenda.this,R.drawable.borderwhite));

                tvMyAgena.setTextColor(ContextCompat.getColor(Agenda.this, R.color.white));
                tvMyAgena.setBackgroundResource(android.R.color.transparent);
                Utils.goToFragment(Agenda.this,AgendaFragment.newInstance(),null,null);
            }
        });
    }

    private void findViewById() {
        tvAgena = (TextView) findViewById(R.id.tv_agena);
        tvMyAgena = (TextView) findViewById(R.id.tv_my_agena);
    }
}
