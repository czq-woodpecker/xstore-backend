package com.woodpecker.czq.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity handleException(BindException bindException) {
        List<ObjectError> errors = bindException.getAllErrors();
        FieldError error = (FieldError)errors.get(0);
        String field = error.getField();
        String message = error.getDefaultMessage();
        return ResponseEntity
                .status(400)
                .body(field + message);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception exception) {
        return ResponseEntity
                .status(400)
                .body("服务器异常");
    }
}
