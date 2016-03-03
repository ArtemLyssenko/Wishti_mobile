package com.prototype.wishti;

import android.app.Application;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import net.danlew.android.joda.JodaTimeAndroid;

import org.androidannotations.annotations.EApplication;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;

@EApplication
public class WishtiApp extends Application {

    public final static  String ROOT_URL = "http://wishti.ddns.net:4545/api";
    private static final String TWITTER_KEY = "VnfsZHMHAzSGXBHQnvWNZG4eS";
    private static final String TWITTER_SECRET = "AUgQtxFbPTfu79kho3SCelNJ2kIcH56x5YmN7z0hlAWjflZMMO";


    public static String[] MONTHS;
    public static String[] WEEKDAYS;
    public static String[] WEEKDAYS_SHORT;

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());

        JodaTimeAndroid.init(this);


        MONTHS = getResources().getStringArray(R.array.months);
        WEEKDAYS = getResources().getStringArray(R.array.weekdays);
        WEEKDAYS_SHORT = getResources().getStringArray(R.array.weekdays_short);
    }
}
