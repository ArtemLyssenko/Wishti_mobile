package com.prototype.wishti.models;

import org.androidannotations.annotations.EBean;

@EBean
public class Cookie {

    private String value;

    private String domain;

    private String path;

    private String expires;

    private boolean secure;

    private boolean httpOnly;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(String secure) {
        if(secure !=null)
            this.secure = !secure.isEmpty();
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(String httpOnly) {
        if(httpOnly !=null)
            this.httpOnly = !httpOnly.isEmpty();

    }
}
