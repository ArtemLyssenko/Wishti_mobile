package com.prototype.wishti.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.prototype.wishti.R;
import com.prototype.wishti.dtos.BaseServerResponseDto;
import com.prototype.wishti.proxies.AuthorizationServiceClient;
import com.prototype.wishti.sharedPreferences.FirstEntryPref_;
import com.twitter.sdk.android.core.SessionManager;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity
public class CheckIfFirstEntry extends Activity {

    @Pref
    FirstEntryPref_ ifFirst;

    @RestService
    AuthorizationServiceClient authServiceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ifFirst.edit().IfFirstEntry().put(true).apply();


        if(ifFirst.IfFirstEntry().get()){
            Intent myIntent = new Intent(this, FirstEntryActivity_.class);
            this.startActivity(myIntent);
        }else {

            SessionManager<DigitsSession> manager = Digits.getSessionManager();

            //manager.clearActiveSession();

            DigitsSession session = manager.getActiveSession();

            if (session == null || !session.isLoggedOutUser()) {

                Digits.authenticate(new AuthCallback() {

                    @Override
                    public void success(DigitsSession digitsSession, String s) {

                        BaseServerResponseDto dto = authServiceClient.authorize();

                        if(dto.isSuccessful()){
                            Intent myIntent = new Intent(CheckIfFirstEntry.this, MainActivity.class);
                            CheckIfFirstEntry.this.startActivity(myIntent);
                        }

                        Intent myIntent = new Intent(CheckIfFirstEntry.this, FirstEntryActivity_.class);
                        CheckIfFirstEntry.this.startActivity(myIntent);
                    }

                    @Override
                    public void failure(DigitsException e) {
                        e.printStackTrace();

                        Intent myIntent = new Intent(CheckIfFirstEntry.this, FirstEntryActivity_.class);
                        CheckIfFirstEntry.this.startActivity(myIntent);
                    }
                }, R.style.CustomDigitsTheme);

            } else {
                Intent myIntent = new Intent(this, FirstEntryActivity_.class);
                this.startActivity(myIntent);
            }
        }

    }
}
