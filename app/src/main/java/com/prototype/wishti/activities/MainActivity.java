package com.prototype.wishti.activities;

import android.app.Activity;
import android.os.Bundle;

import com.prototype.wishti.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_profile);



//        PusherNotificationService_.intent(this).extra("channelId", "123456").start();
//
//        Intent notificationServiceIntent = new Intent(this, PusherNotificationService.class);
//        notificationServiceIntent.putExtra("channelId", "123456");
//        startService(notificationServiceIntent);

    }

}
