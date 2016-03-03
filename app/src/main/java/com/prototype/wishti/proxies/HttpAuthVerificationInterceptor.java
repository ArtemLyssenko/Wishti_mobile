package com.prototype.wishti.proxies;

import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsOAuthSigning;
import com.digits.sdk.android.DigitsSession;
import com.prototype.wishti.helpers.CookieStoreUtility;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@EBean
public class HttpAuthVerificationInterceptor implements ClientHttpRequestInterceptor {

    final String SET_COOKIE_HEADER = "Set-Cookie";

    @Bean
    CookieStoreUtility cookieStoreUtility;


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        DigitsSession activeSession = Digits.getSessionManager().getActiveSession();

        TwitterAuthToken token = (TwitterAuthToken) activeSession.getAuthToken();
        TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();

        DigitsOAuthSigning oauthSigning = new DigitsOAuthSigning(authConfig, token);

        Map<String, String> authHeaders = oauthSigning.getOAuthEchoHeadersForVerifyCredentials();


        for (Map.Entry<String, String> entry : authHeaders.entrySet())
        {
            request.getHeaders().add(entry.getKey(), entry.getValue());
        }

        ClientHttpResponse response = execution.execute(request, body);

        List<String> setCookieHeaderValue = response.getHeaders().get(SET_COOKIE_HEADER);

        if(setCookieHeaderValue!=null)
            if(!setCookieHeaderValue.isEmpty()){
                String authSessionId = setCookieHeaderValue.get(0);

                cookieStoreUtility.setCookie(authSessionId);
        }

        return response;
    }
}
