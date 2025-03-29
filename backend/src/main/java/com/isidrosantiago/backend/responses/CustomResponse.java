package com.isidrosantiago.backend.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class CustomResponse<T> {
    private String status;
    private T data;
    private String message;
    private String internalErrorCode;

    public CustomResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public CustomResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CustomResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomResponse(String status, String message, String internalErrorCode) {
        this.status = status;
        this.message = message;
        this.internalErrorCode = internalErrorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInternalErrorCode() {
        return internalErrorCode;
    }

    public void setInternalErrorCode(String internalErrorCode) {
        this.internalErrorCode = internalErrorCode;
    }
}
