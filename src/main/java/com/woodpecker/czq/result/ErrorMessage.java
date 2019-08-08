package com.woodpecker.czq.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    private int code;
    private String message;

    public static final ErrorMessage SERVER_ERROR = new ErrorMessage(4000, "服务端异常");
    public static final ErrorMessage BIND_ERROR = new ErrorMessage(4001, "参数校验异常：%s");
    public static final ErrorMessage SERVICE_ERROR = new ErrorMessage(4002, "%s");

    public ErrorMessage fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.message, args);
        return new ErrorMessage(code, message);
    }
}
