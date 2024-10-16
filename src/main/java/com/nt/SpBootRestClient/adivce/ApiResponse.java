package com.nt.SpBootRestClient.adivce;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime timeStamp;
    private  T data;
    private ApiError error;

    public ApiResponse() {
        this.timeStamp = timeStamp;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}
