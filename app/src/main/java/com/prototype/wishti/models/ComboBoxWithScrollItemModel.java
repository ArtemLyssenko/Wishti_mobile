package com.prototype.wishti.models;

public class ComboBoxWithScrollItemModel {

    private String text;
    private int value;
    private boolean selected = false;

    public ComboBoxWithScrollItemModel(){}

    public ComboBoxWithScrollItemModel(String text, int value, Boolean isSelected){
        this.text = text;
        this.value = value;
        this.selected = isSelected;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
