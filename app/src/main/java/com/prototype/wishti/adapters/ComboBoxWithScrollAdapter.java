package com.prototype.wishti.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.prototype.wishti.R;
import com.prototype.wishti.custom_views.ComboBoxWithScrollItemView;
import com.prototype.wishti.interfaces.OnComboBoxItemSelectedListener;
import com.prototype.wishti.models.ComboBoxWithScrollItemModel;

import java.util.List;


public class ComboBoxWithScrollAdapter extends BaseAdapter {

    private Context context;
    private OnComboBoxItemSelectedListener listener;
    private List<ComboBoxWithScrollItemModel> items;
    private ComboBoxWithScrollItemView selectedItem;

    public ComboBoxWithScrollAdapter(Context context, List<ComboBoxWithScrollItemModel> items){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.items.size();
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

        ComboBoxWithScrollItemModel model = this.items.get(position);

        ComboBoxWithScrollItemView itemView = new ComboBoxWithScrollItemView(this.context);

        itemView.setItemText(model.getText());
        itemView.setItemValue(model.getValue());
        itemView.setSelected(model.isSelected());

        if(model.isSelected()) {
            itemView.setBackColor(R.drawable.border_gray_top_with_light_gray_body);
            selectedItem = itemView;
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (selectedItem != null)
                    selectedItem.setBackColor(R.drawable.border_top_gray_with_white_body);

                ComboBoxWithScrollItemView selectedItem = (ComboBoxWithScrollItemView) v;
                ComboBoxWithScrollAdapter.this.selectedItem = selectedItem;
                selectedItem.setBackColor(R.drawable.border_gray_top_with_light_gray_body);

                if (listener != null)
                    listener.OnComboBoxItemSelected(selectedItem);
            }
        });

        return itemView;
    }

    public void setOnComboBoxItemSelectListener(OnComboBoxItemSelectedListener listener){
        this.listener = listener;
    }
}
