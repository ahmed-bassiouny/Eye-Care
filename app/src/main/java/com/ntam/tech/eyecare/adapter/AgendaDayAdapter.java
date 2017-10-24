package com.ntam.tech.eyecare.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ntam.tech.eyecare.R;

public class AgendaDayAdapter extends BaseAdapter {
    LayoutInflater inflator;
    List<String> days;

    public AgendaDayAdapter(Context context) {
        inflator = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflator.inflate(R.layout.item_agenda_day, null);
        TextView tvDay = convertView.findViewById(R.id.tv_day);
        TextView tvDate = convertView.findViewById(R.id.tv_date);
        String[] myday = days.get(position).split(",");
        tvDay.setText(myday[0]);
        tvDate.setText(myday[1]);
        return convertView;
    }
    public void setDays(List<String> days){
        this.days=days;
    }
}