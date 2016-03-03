package com.prototype.wishti.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.prototype.wishti.R;


public class ComboBoxWithScrollItemView extends RelativeLayout {

    private TextViewWithFont itemText;
    private RelativeLayout itemBack;
    private int itemValue;

    public ComboBoxWithScrollItemView(Context context) {
        super(context);
        initComponent();
    }

    public ComboBoxWithScrollItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    public ComboBoxWithScrollItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent();
    }

    private void initComponent(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.combobox_with_scroll_item_layout, this);

        this.itemText = (TextViewWithFont)findViewById(R.id.item_text);
        this.itemBack = (RelativeLayout)findViewById(R.id.item_back);
    }

    public void setItemValue(int value){
        this.itemValue = value;
    }

    public int getItemValue(){
        return this.itemValue;
    }

    public void setItemText(String text){
        if(this.itemText !=null)
            this.itemText.setText(text);
    }

    public void setBackColor(int color){
        this.itemBack.setBackgroundResource(color);
    }

    public String getItemText(){
        return this.itemText.getText().toString();
    }
}
