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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.prototype.wishti.R;
import com.prototype.wishti.interfaces.OnCheckListener;

public class CheckBoxWithArrow extends RelativeLayout implements OnClickListener{

    private boolean isChecked = false;

    private OnCheckListener listener;

    public CheckBoxWithArrow(Context context) {
        super(context);
        initComponent();
    }

    public CheckBoxWithArrow(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    public CheckBoxWithArrow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent();
    }

    private void initComponent(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.checkbox_with_arrow_layout, this);
        this.setOnClickListener(this);
    }

    public void setOnCheckListener(OnCheckListener listener){
        this.listener = listener;
    }

    public boolean isChecked(){
        return this.isChecked;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {

        ViewGroup root = (ViewGroup)findViewById(R.id.checkbox_container);

        TransitionInflater inflater = TransitionInflater.from(view.getContext());
        Transition transition
                = inflater.inflateTransition(R.transition.checkbox_with_arrow_transition);

        final Scene scene2 = Scene.getSceneForLayout(root, R.layout.checkbox_with_arrow_checked_scene, view.getContext());
        final Scene scene1 = Scene.getSceneForLayout(root, R.layout.checkbox_with_arrow_unchecked_scene, view.getContext());


        if(isChecked){
            TransitionManager.go(scene1, transition);
            isChecked = false;
        }else {
            TransitionManager.go(scene2, transition);
            isChecked = true;
        }

        if(listener!=null)
            listener.OnCheck(view);
    }
}
