package com.prototype.wishti.proxies;

import com.prototype.wishti.sharedPreferences.CookieSessionIdPrefs_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@EBean
public class HttpCookieInterceptor implements ClientHttpRequestInterceptor {

    final String COOKIE_HEADER = "Cookie";

    @Pref
    CookieSessionIdPrefs_ cookieSessionIdPrefsPrefs;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        String cookieSessionId = cookieSessionIdPrefsPrefs.CookieSessionId().get();

        if(cookieSessionId !=null && !cookieSessionId.isEmpty())
            request.getHeaders().add(COOKIE_HEADER,cookieSessionId);

        ClientHttpResponse response = execution.execute(request, body);


        return response;
    }
}
