package com.prototype.wishti.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.widget.Toast;

import com.prototype.wishti.activities.MainActivity;
import com.prototype.wishti.helpers.NotificationUtility;
import com.prototype.wishti.helpers.PusherUtility;
import com.pusher.client.channel.SubscriptionEventListener;

import org.androidannotations.annotations.EService;

@EService
public class PusherNotificationService extends Service {

    protected void onHandleIntent(Intent intent) {

        Toast("Service started");

        String channelId = "123456";///intent.getExtras().get("channelId").toString();

        SubscriptionEventListener listener = new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, String data) {
                switch (eventName){
                    case PusherUtility.NEW_APPOINTMENT :
                        showNotification("New appointment","new appointment has created",MainActivity.class);
                        break;
                    case PusherUtility.NEW_APPOINTMENT_DRAFT :
                        showNotification("New appointment draft","new appointment draft has created",MainActivity.class);
                        break;
                    case PusherUtility.APPOINTMENT_STATUS_CHANGED :
                        showNotification("Appointment №3","appointment №3 has changed",MainActivity.class);
                        break;
                    case PusherUtility.APPOINTMENT_DRAFT_STATUS_CHANGED :
                        showNotification("Appointment draft №45","appointment draft status has changed",MainActivity.class);
                        break;
                }
            }
        };

        PusherUtility utility = new PusherUtility(listener);

        utility.connect(channelId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        onHandleIntent(intent);

        return START_STICKY;
    }

    private void showNotification(String title, String body, Class<?> startActivity){

        NotificationUtility notificationUtility = NotificationUtility.getInstance(this);
        notificationUtility.createInfoNotification(title,body,startActivity);

    }

    @UiThread
    void Toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        Toast("onDestroy");

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
