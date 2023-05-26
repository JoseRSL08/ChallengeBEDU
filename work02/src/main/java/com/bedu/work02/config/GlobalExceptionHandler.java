package com.bedu.work02.config;

import com.bedu.work02.exception.CurrencyAlreadyExistsException;
import com.bedu.work02.exception.CurrencyNotFoundException;
import com.bedu.work02.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(CurrencyNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getCurrency() + " was not found in catalog of currencies"));
    }

    @ExceptionHandler(CurrencyAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Currency already exists"));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Something wrong happened..."));
    }

}
