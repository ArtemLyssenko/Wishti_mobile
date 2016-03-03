package com.prototype.wishti.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.prototype.wishti.R;
import com.prototype.wishti.custom_views.TextViewWithFont;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.first_entry_activity)
public class FirstEntryActivity extends Activity {

    @ViewById(R.id.scene_root)
    FrameLayout root;

    @ViewById(R.id.get_started_button)
    TextViewWithFont getStartedButton;


    @AfterViews
    void afterViews(){

        this.getWindow().setStatusBarColor(Color.TRANSPARENT);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        root.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                root.getViewTreeObserver().removeOnPreDrawListener(this);
                startAnimation();

                return true;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    void startAnimation(){

        final Scene scene2 = Scene.getSceneForLayout(root, R.layout.first_entry_scene_two, root.getContext());

        final TransitionSet set = new TransitionSet();
        set.setStartDelay(1000);
        set.setDuration(1000);
        set.setOrdering(TransitionSet.ORDERING_TOGETHER);
        set.addTarget(R.id.circle_logo);
        set.addTarget(R.id.title_logo);
        set.addTarget(R.id.intro_description);



        Fade fadeIn= new Fade(Fade.IN);

        ChangeBounds bounds = new ChangeBounds();

        set.addTransition(fadeIn);
        set.addTransition(bounds);

        TransitionManager.go(scene2, set);

    }

    public void click_button(View view) {
        Digits.authenticate(new AuthCallback() {
            public void success(DigitsSession digitsSession, String s) {
                if(!digitsSession.isLoggedOutUser()){

                    Intent myIntent = new Intent(FirstEntryActivity.this, MainActivity.class);
                    FirstEntryActivity.this.startActivity(myIntent);
                }
            }

            @Override
            public void failure(DigitsException e) {
                e.printStackTrace();
            }
        },R.style.CustomDigitsTheme);
    }
}
