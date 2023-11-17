package com.example.ontapandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ontapandroid.model.ThiSinh;

import java.util.List;

public class ThiSinhAdapter extends ArrayAdapter<ThiSinh> {
    public ThiSinhAdapter(@NonNull Context context,  @NonNull List<ThiSinh> thiSinhList){
        super(context, 0, thiSinhList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_activity, parent, false);
        }
        ThiSinh thiSinh =getItem(position);
        TextView sobaodanhthisinh = convertView.findViewById(R.id.sobaodanhthisinh);
        TextView hotenthisinh = convertView.findViewById(R.id.hotenthisinh);
        TextView diemtb = convertView.findViewById(R.id.diemtb);


        if (thiSinh != null) {
            sobaodanhthisinh.setText("" + thiSinh.getSobaodanh());
            hotenthisinh.setText("" + thiSinh.getTenTS());
            double tongDiem = thiSinh.getDiemly() + thiSinh.getDiemtoan() + thiSinh.getDiemhoa();
            diemtb.setText(tongDiem+"");
        }

        return convertView;
    }
}
