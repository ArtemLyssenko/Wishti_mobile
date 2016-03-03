package com.prototype.wishti.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.prototype.wishti.custom_views.CalendarItemViewHorizontal;
import com.prototype.wishti.interfaces.OnCalendarItemSelectedListener;
import com.prototype.wishti.models.CalendarItemModel;
import com.prototype.wishti.R;
import com.prototype.wishti.helpers.CalendarUtility;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CalendarViewHorizontalAdapter extends BaseAdapter {

    private final int CHART_MAX_VALUE = 100;

    private Context context;
    private DateTime dateTime;
    private List<CalendarItemModel> calendarItems;
    private CalendarItemViewHorizontal today;
    private OnCalendarItemSelectedListener listener;

    public CalendarViewHorizontalAdapter(Context context, DateTime startDateTime){

        this.context = context;

        this.dateTime = startDateTime;

        this.calendarItems = new ArrayList<>();

        initModel();
    }

    private void initModel(){

        int today = dateTime.getDayOfMonth();

        int days = dateTime.dayOfMonth().getMaximumValue();

        int month = dateTime.getMonthOfYear();

        int year = dateTime.getYear();

        Random random = new Random();

        for (int i = 1; i<=days; i++){

            CalendarItemModel model = new CalendarItemModel();
            model.day = i;
            model.month = CalendarUtility.getNameOfMonth(month);
            model.year = year;
            model.AppointmentCount = random.nextInt(CHART_MAX_VALUE);


            if(i == today){
                model.isToday = true;
            }

            calendarItems.add(model);
        }
    }

    @Override
    public int getCount() {
        return this.calendarItems.size();
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

        if(convertView !=null)
            return convertView;

        CalendarItemViewHorizontal item = new CalendarItemViewHorizontal(this.context);

        boolean isToday = this.calendarItems.get(position).isToday;
        int day = this.calendarItems.get(position).day;
        int appointmentCount = this.calendarItems.get(position).AppointmentCount;


        if(isToday && new DateTime().getMonthOfYear() == dateTime.getMonthOfYear()){
            item.setColorForMainContainer(R.color.darkGrey);
            this.today = item;
        }

        if(new DateTime().getDayOfMonth() < day && new DateTime().getMonthOfYear() == this.dateTime.getMonthOfYear()){
            item.setFutureDay();
        }

        int dayOfWeek = new DateTime(this.dateTime.getYear(),this.dateTime.getMonthOfYear(),day,0,0).getDayOfWeek();

        item.setValueForBackgroundChart(appointmentCount);
        item.setDate(String.valueOf(day));
        item.setWeekDay(CalendarUtility.getWeekday(dayOfWeek, CalendarUtility.OutputType.SHORT));

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today = (CalendarItemViewHorizontal) v;
                if (listener != null) {
                    listener.OnCalendarItemSelected((CalendarItemViewHorizontal) v);
                }
            }
        });


        return item;
    }

    public void setOnCalendarItemSelectListener(OnCalendarItemSelectedListener listener){
        this.listener = listener;
    }

    public CalendarItemViewHorizontal getItemToday() {
        return this.today;
    }
}
