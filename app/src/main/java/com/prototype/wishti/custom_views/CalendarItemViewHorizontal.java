package com.prototype.wishti.custom_views;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.prototype.wishti.R;


public class CalendarItemViewHorizontal extends LinearLayout{

    private TextViewWithFont date;
    private TextViewWithFont day;
    private LinearLayout backgroundChart;
    private RelativeLayout itemContainer;


    public CalendarItemViewHorizontal(Context context) {
        super(context);
        initComponent();
    }

    private void initComponent() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_item_view_layout, this);

        date = (TextViewWithFont)findViewById(R.id.date);
        day = (TextViewWithFont)findViewById(R.id.day);

        itemContainer = (RelativeLayout)findViewById(R.id.item_container);
        backgroundChart = (LinearLayout)findViewById(R.id.background_chart);
    }

    public void setValueForBackgroundChart(int value){
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)backgroundChart.getLayoutParams();
        lp.setMargins(0, value, 0, 0);
    }


    public void setColorForMainContainer(int colorResource){
        itemContainer.setBackgroundResource(colorResource);
    }

    public void setFutureDay(){
        this.itemContainer.setBackgroundResource(R.color.lightGray);
        this.date.setTextColor(ContextCompat.getColor(this.getContext(),android.R.color.black));
        this.date.setAlpha(0.4f);
        this.day.setTextColor(ContextCompat.getColor(this.getContext(), android.R.color.black));
        this.day.setAlpha(0.4f);
    }


    public void setDate(String date){
        this.date.setText(date);
    }

    public void setWeekDay(String day){
        this.day.setText(day);
    }

    public String getWeekDay(){
        return this.day.getText().toString();
    }

    public int getDate(){
        return Integer.valueOf(this.date.getText().toString());
    }
}

