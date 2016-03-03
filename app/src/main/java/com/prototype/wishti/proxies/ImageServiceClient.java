package com.prototype.wishti.proxies;

import com.prototype.wishti.WishtiApp;
import com.prototype.wishti.dtos.BaseServerResponseDto;
import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresCookie;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;

@Rest(rootUrl = WishtiApp.ROOT_URL, converters = {
        ByteArrayHttpMessageConverter.class,
        MappingJackson2HttpMessageConverter.class,}, interceptors = { HttpCookieInterceptor.class})
public interface ImageServiceClient {

    @Post("/img/download")
    @RequiresHeader("Content_Type")
    BaseServerResponseDto uploadImg(MultiValueMap multiPartContent);

    @Post("/img/upload")
    @Accept(MediaType.APPLICATION_OCTET_STREAM)
    byte[] downloadImg();

}
