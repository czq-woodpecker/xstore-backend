package com.woodpecker.czq.exception;


public class ServiceException extends RuntimeException{
    private String msg;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }
}
