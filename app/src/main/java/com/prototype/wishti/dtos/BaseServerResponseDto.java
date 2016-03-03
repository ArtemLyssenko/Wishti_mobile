package com.prototype.wishti.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseServerResponseDto {

    @JsonProperty("IsSuccessful")
    private boolean isSuccessful;

    @JsonProperty("Error")
    private String error;

    @JsonProperty("StackTrace")
    private String stackTrace;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
