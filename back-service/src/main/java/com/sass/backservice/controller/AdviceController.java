package com.sass.backservice.controller;

import com.sass.backservice.dto.ResponseDto;
import com.sass.backservice.exceptions.CounterTypeException;
import com.sass.backservice.exceptions.MLExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ResponseDto> handleUnexpectedType(RuntimeException ex) {
        return processHandler(ex.getMessage());
    }

    @ExceptionHandler(value = MLExecutionException.class)
    public ResponseEntity<ResponseDto> handleExecutionException(MLExecutionException ex) {
        return processHandler(ex.getMessage());
    }

    @ExceptionHandler(value = CounterTypeException.class)
    public ResponseEntity<ResponseDto> handleCounterTypeException(CounterTypeException ex) {
        return processHandler(ex.getMessage());
    }

    private ResponseEntity<ResponseDto> processHandler(String message) {
        ResponseDto responseDto = new ResponseDto(message);
        return new ResponseEntity<>(responseDto, INTERNAL_SERVER_ERROR);
    }
}
