package com.woodpecker.czq.result;

import lombok.Getter;

@Getter
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(String message, T data) {
        this.code = 200;
        this.data = data;
        this.message = message;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        this.code = 200;
        this.data = data;
    }

    public Result(ErrorMessage errorMessage) {
        if(errorMessage == null) {
            return;
        }
        this.code = errorMessage.getCode();
        this.message = errorMessage.getMessage();
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> error(ErrorMessage errorMessage) {
        return new Result<T>(errorMessage);
    }

}
