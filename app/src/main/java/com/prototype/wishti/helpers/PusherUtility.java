package com.prototype.wishti.helpers;


import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

public class PusherUtility {

    public static final String NEW_APPOINTMENT = "1";
    public static final String APPOINTMENT_STATUS_CHANGED = "2";
    public static final String NEW_APPOINTMENT_DRAFT = "3";
    public static final String APPOINTMENT_DRAFT_STATUS_CHANGED = "4";

    SubscriptionEventListener listener;

    public PusherUtility(SubscriptionEventListener listener){

        this.listener = listener;
    }

    private static final String YOUR_APP_KEY = "65f852f2f2a55b574562";

    public void connect(String channelId){
        Pusher pusher = new Pusher(YOUR_APP_KEY);

        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {

            }

            @Override
            public void onError(String message, String code, Exception e) {


            }
        }, ConnectionState.ALL);

        Channel channel = pusher.subscribe(channelId);


        channel.bind(NEW_APPOINTMENT,listener);
        channel.bind(APPOINTMENT_STATUS_CHANGED,listener);
        channel.bind(NEW_APPOINTMENT_DRAFT,listener);
        channel.bind(APPOINTMENT_DRAFT_STATUS_CHANGED,listener);


        pusher.disconnect();

        pusher.connect();

    }
}
