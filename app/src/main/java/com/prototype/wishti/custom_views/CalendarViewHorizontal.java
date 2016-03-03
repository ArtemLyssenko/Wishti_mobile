package com.prototype.wishti.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.prototype.wishti.R;
import com.prototype.wishti.adapters.CalendarViewHorizontalAdapter;
import com.prototype.wishti.helpers.CalendarUtility;
import com.prototype.wishti.interfaces.OnCalendarItemSelectedListener;

import org.joda.time.DateTime;

public class CalendarViewHorizontal extends LinearLayout {

    private final int STEP = 1;
    private Context context;
    private TextViewWithFont nextMonth;
    private TextViewWithFont currentMonth;
    private TextViewWithFont previousMonth;
    private HorizontalScrollView scrollView;
    private CalendarViewHorizontalItemsContainer calendarContainer;

    private DateTime currentDateTime;

    public CalendarViewHorizontal(Context context) {
        super(context);
        initComponent();
        this.context = context;
    }

    public CalendarViewHorizontal(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
        this.context = context;
    }


    public CalendarViewHorizontal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent();
        this.context = context;
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void initComponent() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_view_horizontal_layout, this);

        this.scrollView = (HorizontalScrollView) findViewById(R.id.scroll_view_horizontal);
        this.calendarContainer = (CalendarViewHorizontalItemsContainer) findViewById(R.id.calendar_view_horizontal_container);
        this.nextMonth = (TextViewWithFont)findViewById(R.id.next_month);
        this.previousMonth = (TextViewWithFont)findViewById(R.id.previous_month);
        this.currentMonth = (TextViewWithFont)findViewById(R.id.current_month);

        this.currentDateTime = new DateTime();
        this.currentMonth.setText(currentDateTime.monthOfYear().getAsText().toUpperCase());
        this.previousMonth.setText(currentDateTime.minusMonths(STEP).monthOfYear().getAsText().toUpperCase());
        this.nextMonth.setText(currentDateTime.plusMonths(STEP).monthOfYear().getAsText().toUpperCase());


//        Sticky Scroll
//
//        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//
//            @Override
//            public void onScrollChanged() {
//
//                int scrollX = scrollView.getScrollX();
//
//                int t = calendarContainer.getChildCount();
//
//                int[] array = new int[t];
//
//                float density = getResources().getDisplayMetrics().density;
//
//                int dpi = (int) density;
//
//                int allElements = 72 * t * dpi;
//
//                int dpiOfOneItem = 72 * dpi;
//
//                for (int i = 0; i < t; i++) {
//                    array[i] = i * 72 * dpi;
//                }
//
//                for (int i = 0; i < array.length; i++) {
//                    if (scrollX > array[i] && scrollX < array[i] + dpiOfOneItem) {
//                        scrollView.scrollTo(array[i] + dpiOfOneItem, 0);
//                        break;
//                    }
//                }
//
//            }
//        });


        nextMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                currentDateTime = currentDateTime.plusMonths(STEP);
                CalendarViewHorizontalAdapter adapter = new CalendarViewHorizontalAdapter(context, currentDateTime);
                calendarContainer.setAdapter(adapter);

                currentMonth.setText(CalendarUtility.getNameOfMonth(currentDateTime.getMonthOfYear()).toUpperCase());
                previousMonth.setText(CalendarUtility.getNameOfMonth(currentDateTime.minusMonths(STEP).getMonthOfYear()).toUpperCase());
                nextMonth.setText(CalendarUtility.getNameOfMonth(currentDateTime.plusMonths(STEP).getMonthOfYear()).toUpperCase());

                if (currentDateTime.getMonthOfYear() != new DateTime().getMonthOfYear())
                    scrollView.scrollTo(0, 0);
                else {
                    autoScroll();
                }
            }
        });

        previousMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                currentDateTime = currentDateTime.minusMonths(STEP);
                CalendarViewHorizontalAdapter adapter = new CalendarViewHorizontalAdapter(context, currentDateTime);
                calendarContainer.setAdapter(adapter);

                currentMonth.setText(CalendarUtility.getNameOfMonth(currentDateTime.getMonthOfYear()).toUpperCase());
                previousMonth.setText(CalendarUtility.getNameOfMonth(currentDateTime.minusMonths(STEP).getMonthOfYear()).toUpperCase());
                nextMonth.setText(CalendarUtility.getNameOfMonth(currentDateTime.plusMonths(STEP).getMonthOfYear()).toUpperCase());

                if (currentDateTime.getMonthOfYear() != new DateTime().getMonthOfYear())
                    scrollView.scrollTo(0,0);
                else {
                    autoScroll();
                }
            }
        });
    }

    public CalendarViewHorizontalItemsContainer getCalendarContainer(){
        return this.calendarContainer;
    }

    public DateTime getCurrentDateTime(){
        return this.currentDateTime;
    }

    public void autoScroll(){
        float density = getResources().getDisplayMetrics().density;

        int dpi = (int) density;

        int scrollTo;

        if (dpi == 0)
            dpi = 1;

        DateTime model = new DateTime();
        int today = model.getDayOfMonth();
        int lastDayOfMonth = model.monthOfYear().getMinimumValue();

        if (today == lastDayOfMonth)
            scrollTo = 72 * today * dpi;
        else if (today == 1)
            scrollTo = 1;
        else
            scrollTo = 72 * (today - 3) * dpi;

        scrollView.scrollTo(scrollTo, 0);
    }

    public void setOnCalendarItemSelectListener(OnCalendarItemSelectedListener listener){
        this.calendarContainer.setOnCalendarItemSelectListener(listener);
    }


    public Adapter getAdapter(){
        return this.calendarContainer.getAdapter();
    }

    public void setAdapter(CalendarViewHorizontalAdapter adapter){
        this.calendarContainer.setAdapter(adapter);
    }
}
