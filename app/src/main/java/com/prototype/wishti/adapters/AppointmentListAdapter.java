package com.prototype.wishti.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.prototype.wishti.AppointmentListItem;

public class AppointmentListAdapter extends BaseAdapter{

    Context context;
    AppointmentListItem[] items;

    public AppointmentListAdapter(Context context){
        this.context = context;
        this.items = new AppointmentListItem[30];
        for(int i = 0; i<this.items.length; i++){

            items[i] = new AppointmentListItem(context);
        }
    }

    @Override
    public int getCount() {
        return this.items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return items[position];
    }
}
