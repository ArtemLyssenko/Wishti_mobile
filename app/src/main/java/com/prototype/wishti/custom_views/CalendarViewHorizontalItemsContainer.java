package com.prototype.wishti.custom_views;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.prototype.wishti.adapters.CalendarViewHorizontalAdapter;
import com.prototype.wishti.interfaces.OnCalendarItemSelectedListener;

public class CalendarViewHorizontalItemsContainer extends LinearLayout {

    private CalendarViewHorizontalAdapter adapter;
    private final DataSetObserver observer = new DataSetObserver() {

        @Override
        public void onChanged() {
            refreshViewsFromAdapter();
        }

        @Override
        public void onInvalidated() {
            removeAllViews();
        }
    };


    public CalendarViewHorizontalItemsContainer(Context context) {
        super(context);
    }

    public CalendarViewHorizontalItemsContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarViewHorizontalItemsContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CalendarViewHorizontalAdapter getAdapter() {
        return adapter;
    }

    public CalendarItemViewHorizontal getItemToday() {
        return this.adapter.getItemToday();
    }

    public void setOnCalendarItemSelectListener(OnCalendarItemSelectedListener listener){
        if(this.adapter != null)
            this.adapter.setOnCalendarItemSelectListener(listener);
    }


    public void setAdapter(CalendarViewHorizontalAdapter adapter) {
        if (this.adapter != null) {
            this.adapter.unregisterDataSetObserver(observer);
        }

        this.adapter = adapter;
        if (this.adapter != null) {
            this.adapter.registerDataSetObserver(observer);
        }
        initViewsFromAdapter();
    }

    protected void initViewsFromAdapter() {
        removeAllViews();
        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                addView(adapter.getView(i, null, this), i);
            }
        }
    }

    protected void refreshViewsFromAdapter() {
        int childCount = getChildCount();
        int adapterSize = adapter.getCount();
        int reuseCount = Math.min(childCount, adapterSize);

        for (int i = 0; i < reuseCount; i++) {
            adapter.getView(i, getChildAt(i), this);
        }

        if (childCount < adapterSize) {
            for (int i = childCount; i < adapterSize; i++) {
                addView(adapter.getView(i, null, this), i);
            }
        } else if (childCount > adapterSize) {
            removeViews(adapterSize, childCount);
        }
    }
}
