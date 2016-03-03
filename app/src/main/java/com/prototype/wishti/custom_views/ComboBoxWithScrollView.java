package com.prototype.wishti.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.prototype.wishti.R;
import com.prototype.wishti.adapters.ComboBoxWithScrollAdapter;
import com.prototype.wishti.interfaces.OnComboBoxItemSelectedListener;


public class ComboBoxWithScrollView extends LinearLayout {

    private final int ANIMATION_DURING = 500;
    private final int MAXIMUM_VISIBLE_ITEMS_IN_SCROLL_VIEW = 5;
    private final int ONE_ITEM_HEIGHT = 50;

    private View cancelButton;
    private View background;
    private ScrollView scrollView;
    private ComboBoxWithScrollViewItemsContainer itemsContainer;
    private TextViewWithFont header;
    private TextViewWithFont bottomButton;

    public ComboBoxWithScrollView(Context context) {
        super(context);
        initComponent();
    }

    public ComboBoxWithScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    public ComboBoxWithScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent();
    }

    private void initComponent(){

        final ComboBoxWithScrollView self = this;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.combobox_with_scroll, this);

        this.setTranslationY(-2000);

        this.scrollView = (ScrollView)findViewById(R.id.comobox_scroll);
        this.itemsContainer = (ComboBoxWithScrollViewItemsContainer)findViewById(R.id.items_container);
        this.cancelButton = findViewById(R.id.cancel_button);
        this.header = (TextViewWithFont)findViewById(R.id.header);
        this.bottomButton = (TextViewWithFont)findViewById(R.id.bottom_button);


        this.cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                self.hide();
            }
        });


        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                getViewTreeObserver().removeOnGlobalLayoutListener(this);

                ViewGroup parent = (ViewGroup) self.getParent();

                background = new View(parent.getContext());

                background.setLayoutParams(
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                background.setBackgroundResource(android.R.color.black);
                background.setAlpha(0.0f);


                parent.removeView(self);
                parent.addView(background);
                parent.addView(self);

            }
        });

    }

    public void setAdapter(ComboBoxWithScrollAdapter adapter){

        float density = getResources().getDisplayMetrics().density;
        int scrollViewHeight = (int)(MAXIMUM_VISIBLE_ITEMS_IN_SCROLL_VIEW * ONE_ITEM_HEIGHT * density);

        if(this.scrollView.getLayoutParams() != null && adapter.getCount() > MAXIMUM_VISIBLE_ITEMS_IN_SCROLL_VIEW)
            this.scrollView.getLayoutParams().height = scrollViewHeight;

        this.itemsContainer.setAdapter(adapter);
    }

    public Adapter getAdapter(){
        return this.itemsContainer.getAdapter();
    }

    public void setOnClickCancelButtonListener(OnClickListener listener){
        if(this.cancelButton != null)
            this.cancelButton.setOnClickListener(listener);
    }

    public void setOnComboBoxItemSelectListener(OnComboBoxItemSelectedListener listener){
        if(this.itemsContainer !=null && this.itemsContainer.getAdapter() !=null)
            this.itemsContainer.getAdapter().setOnComboBoxItemSelectListener(listener);

    }

    public void show(){

        this.animate().translationY(50).setDuration(ANIMATION_DURING).start();

        if(this.background != null)
            this.background.animate().alpha(0.5f).setDuration(ANIMATION_DURING).start();
    }

    public void hide(){

        this.animate().translationY(-2000).setDuration(ANIMATION_DURING).start();

        if(this.background != null)
            this.background.animate().alpha(0.0f).setDuration(ANIMATION_DURING).start();
    }

    public void setTextForHeader(String text){
            this.header.setText(text);
    }

    public void setTextForBottomButton(String text){
            this.bottomButton.setText(text);
    }

}
