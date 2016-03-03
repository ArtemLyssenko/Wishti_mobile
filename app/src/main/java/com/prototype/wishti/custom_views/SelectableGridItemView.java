package com.prototype.wishti.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.prototype.wishti.R;

public class SelectableGridItemView extends RelativeLayout {

    private TextViewWithFont wishTime;
    private boolean selected = false;
    private boolean disabled = false;
    private Context context;

    public SelectableGridItemView(Context context) {
        super(context);
        this.context = context;
        initComponent();
    }

    public SelectableGridItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initComponent();
    }

    public SelectableGridItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initComponent();
    }


    private void initComponent() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.selectable_grid_view_item_layout, this);

        wishTime = (TextViewWithFont)findViewById(R.id.time);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void StartTransition(){
        if(disabled) return;

        final String value = wishTime.getText().toString();

        ViewGroup root = (ViewGroup)findViewById(R.id.transition_container);

        TransitionInflater inflater = TransitionInflater.from(this.getContext());
        Transition transition
                = inflater.inflateTransition(R.transition.selectable_grid_view_item_transition);


        final Scene scene2 = Scene.getSceneForLayout(root, R.layout.selectable_grid_item_selected_scene, this.getContext());
        final Scene scene1 = Scene.getSceneForLayout(root, R.layout.selectable_grid_item_unselected_scene, this.getContext());


        if (selected) {

            selected = false;

            TransitionManager.go(scene1, transition);

            TextViewWithFont timeText = (TextViewWithFont)findViewById(R.id.time);

            timeText.setText(value);

        }
        else {
            selected = true;

            TransitionManager.go(scene2, transition);

            TextViewWithFont timeText = (TextViewWithFont)findViewById(R.id.time);

            timeText.setText(value);
        }
    }

    public void isDisabled(boolean value){
        disabled = value;
        if(value)
            wishTime.setAlpha(0.2f);
        else
            wishTime.setAlpha(0.1f);

    }

    public boolean isSelected(){
        return this.selected;
    }

    public void setWishTime(String timeValue){
        wishTime.setText(timeValue);
    }

    public String getWishTime(){
        return wishTime.getText().toString();
    }
}
