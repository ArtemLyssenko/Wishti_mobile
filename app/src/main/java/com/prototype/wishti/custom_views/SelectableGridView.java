package com.prototype.wishti.custom_views;


import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.GridLayout;

public class SelectableGridView extends GridLayout {

    private Adapter adapter;
    private final int COLUMN_COUNT = 5;
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


    public SelectableGridView(Context context) {
        super(context);
        initComponent();
    }

    public SelectableGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    public SelectableGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent();
    }

    private void initComponent() {
        this.setColumnCount(COLUMN_COUNT);
    }


    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
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
