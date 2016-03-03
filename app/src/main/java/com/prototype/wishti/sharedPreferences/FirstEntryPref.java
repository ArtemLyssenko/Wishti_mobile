package com.prototype.wishti.sharedPreferences;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(SharedPref.Scope.UNIQUE)
public interface FirstEntryPref {

    @DefaultBoolean(value = true)
    boolean IfFirstEntry();
}
