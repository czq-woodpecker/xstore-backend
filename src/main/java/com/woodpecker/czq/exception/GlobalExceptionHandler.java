package com.woodpecker.czq.exception;

import com.woodpecker.czq.result.ErrorMessage;
import com.woodpecker.czq.result.Result;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        if (exception instanceof BindException) {
            List<ObjectError> errors = ((BindException) exception).getAllErrors();
            FieldError error = (FieldError) errors.get(0);
            String field = error.getField();
            String message = error.getDefaultMessage();
            return ResponseEntity
                    .status(400)
                    .body(field + message);
        } else if (exception instanceof ServiceException) {
            return ResponseEntity
                    .status(400)
                    .body(((ServiceException) exception).getMsg());
        } else {
            return ResponseEntity
                    .status(400)
                    .body("服务器异常");
        }
    }
}
