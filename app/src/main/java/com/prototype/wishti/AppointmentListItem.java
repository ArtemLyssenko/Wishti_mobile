package com.prototype.wishti;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class AppointmentListItem extends FrameLayout {

    public AppointmentListItem(Context context) {
        super(context);
        initComponent();
    }

    protected void initComponent(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.appointment_list_item, this);
    }
}
