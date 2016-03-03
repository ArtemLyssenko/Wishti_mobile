package com.prototype.wishti.sharedPreferences;


import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(SharedPref.Scope.UNIQUE)
public interface CookieSessionIdPrefs {

    String CookieSessionId();

}
