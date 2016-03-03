package com.prototype.wishti.broadcast_receivers;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.widget.Toast;

import com.prototype.wishti.services.PusherNotificationService_;

import org.androidannotations.annotations.EReceiver;

@EReceiver
public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast(context,"Receiver started");
        Toast toast = Toast.makeText(context,"Receiver started",Toast.LENGTH_LONG);
        toast.show();

        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

            Toast(context,"BOOT COMPLETED event");

            PusherNotificationService_.intent(context).extra("channelId", "123456").start();
        }
    }

    @UiThread
    void Toast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
