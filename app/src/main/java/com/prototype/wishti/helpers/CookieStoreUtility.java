package com.prototype.wishti.helpers;

import com.prototype.wishti.models.Cookie;
import com.prototype.wishti.sharedPreferences.CookieSessionIdPrefs_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@EBean
public class CookieStoreUtility {

    @Pref
    CookieSessionIdPrefs_ cookieSessionIdPrefsPrefs;

    private final String DOMAIN_PARAM = "Domain";
    private final String PATH_PARAM = "Path";
    private final String EXPIRES_PARAM = "Expires";
    private final String SECURE_PARAM = "Secure";
    private final String HTTP_ONLY_PARAM = "HttpOnly";
    private final String COOKIE_NAME = "WishtiCookie";

    /**
     * Date format pattern used to parse HTTP date headers in RFC 1123 format.
     */
    private final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    /**
     * Date format pattern used to parse HTTP date headers in RFC 1036 format.
     */
    private final String PATTERN_RFC1036 = "EEEE, dd-MMM-yyyy HH:mm:ss zzz";

    /**
     * Date format pattern used to parse HTTP date headers in ANSI C
     * <code>asctime()</code> format.
     */
    private final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";





    public boolean isExpired() throws ParseException {

        Cookie cookie = getCookieObject();

        String expiresStr = cookie.getExpires();

        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_RFC1036);
        Date date = formatter.parse(expiresStr);

        Date now = new Date();

        return date.after(now);
    }

    public void setCookie(String cookie){
        if(cookie == null){
            putToPref("");
        } else if(cookie.isEmpty()){
            putToPref("");
        }

        putToPref(cookie);
    }



    public String getCookie(){

        return getFromPref();

    }

    private void putToPref(String value){
        cookieSessionIdPrefsPrefs.edit().CookieSessionId().put(value).apply();
    }

    private String getFromPref(){
        return cookieSessionIdPrefsPrefs.CookieSessionId().get();
    }

    private Cookie getCookieObject(){

        Cookie cookie = new Cookie();

        String cookieStr =  getFromPref();

        if(cookieStr == null){
            return cookie;
        } else if(cookieStr.isEmpty()){
            return cookie;
        }

        String[] cookieParams = cookieStr.split(";");

        for (String param:cookieParams) {

            String paramTrim = param.trim();

            if(paramTrim.startsWith(COOKIE_NAME)){
                cookie.setValue(getValueOfParam(paramTrim));

            }else if(paramTrim.startsWith(DOMAIN_PARAM)){
                cookie.setDomain(getValueOfParam(paramTrim));

            }else if(paramTrim.startsWith(PATH_PARAM)){
                cookie.setPath(getValueOfParam(paramTrim));

            }else if(paramTrim.startsWith(EXPIRES_PARAM)){
                cookie.setExpires(getValueOfParam(paramTrim));

            }else if(paramTrim.startsWith(SECURE_PARAM)){
                cookie.setSecure(SECURE_PARAM);

            }else if(paramTrim.startsWith(HTTP_ONLY_PARAM)){
                cookie.setHttpOnly(HTTP_ONLY_PARAM);

            }
        }

        return cookie;
    }

    private String getValueOfParam(String paramWithValue){
        String[] paramAndValue = paramWithValue.split("=");

        return paramAndValue[1];
    }
}
