package com.prototype.wishti.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.prototype.wishti.custom_views.SelectableGridItemView;

public class SelectableGridViewAdapter extends BaseAdapter {

    private Context context;
    private String[] sourceArray;
    private String currentValue;
    private SelectableGridItemView selectedPreviously;

    public SelectableGridViewAdapter(Context context, String[] array){
        this.sourceArray = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.sourceArray.length;
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

        String value = this.sourceArray[position];

        final SelectableGridItemView view = new SelectableGridItemView(this.context);

        view.setWishTime(value);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedPreviously != null) {
                    selectedPreviously.StartTransition();
                }

                SelectableGridItemView view = (SelectableGridItemView) v;

                currentValue = view.getWishTime();
                view.StartTransition();
                selectedPreviously = view;

            }
        });

        return view;
    }
}
