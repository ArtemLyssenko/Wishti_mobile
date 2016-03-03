package com.prototype.wishti.activities;

import android.graphics.Color;
import android.app.Activity;
import android.view.View;

import com.prototype.wishti.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.create_user_profile_layout)
public class CreateUserProfileActivity extends Activity {


    @AfterViews
    void afterViewCreated(){
        this.getWindow().setStatusBarColor(Color.TRANSPARENT);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}
