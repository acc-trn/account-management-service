package com.acc.accountmanagementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorMessage> customException(CustomException customException) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .error(customException.getError())
                .message(customException.getMessage())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(customException.getStatus()));
    }
}
