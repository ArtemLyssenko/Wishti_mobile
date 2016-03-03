package com.prototype.wishti.proxies;

import com.prototype.wishti.WishtiApp;
import com.prototype.wishti.dtos.AuthorizationServerResponseDto;
import com.prototype.wishti.dtos.UserDto;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@Rest(rootUrl = WishtiApp.ROOT_URL,
        converters = {
                MappingJackson2HttpMessageConverter.class,
                FormHttpMessageConverter.class,
                ResourceHttpMessageConverter.class
        },
        interceptors = {HttpAuthVerificationInterceptor.class, HttpCookieInterceptor.class}
)
public interface AuthorizationServiceClient {

    @Post("/registration")
    AuthorizationServerResponseDto registration(UserDto userDto);

    @Get("/credentials")
    AuthorizationServerResponseDto authorize();

}


