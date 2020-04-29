package com.example.findyourlecturer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<android.net.wifi.ScanResult> wifiList;

    public ListAdapter(Context context, List<ScanResult> wifilist){
        this.context = context;
        this.wifiList = wifilist;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return wifiList.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        View view = convertView;

        if(view == null) {
            view = inflater.inflate(R.layout.list_item, null);
            holder = new Holder();

            holder.tvDetails = (TextView)view.findViewById(R.id.txtwifiname);
            view.setTag(holder);
        }
        else {
            holder = (Holder)view.getTag();
        }
        holder.tvDetails.setText(wifiList.get(position).SSID);
        return view;

    }




    class Holder{
        TextView tvDetails;
    }

}
