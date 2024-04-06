package com.donghuanjie.onlineOrder.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        // 这里可以提供更详细的错误信息或者根据异常的类型进行不同的处理
        return new ResponseEntity<>("Data Integrity Violation happens.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handlePersistenceException(PersistenceException ex) {
        // 这里可以提供更详细的错误信息或者根据异常的类型进行不同的处理
        return new ResponseEntity<>("Duplicate Email Registered.", HttpStatus.CONFLICT);
    }
    // 如果有必要，可以添加更多的异常处理方法来处理不同类型的异常

}
