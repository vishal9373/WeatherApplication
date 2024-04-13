package com.example.WeatherService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ControllerAdvice  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> dataNotFoundValidation(DataNotFoundException gameValidationException){
        return new ResponseEntity<String>(gameValidationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userValidation(UserNotFoundException userNotFoundException){
        return new ResponseEntity<String>(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordValidationException.class)
    public ResponseEntity<String> userValidation(PasswordValidationException passwordValidationException){
        return new ResponseEntity<String>(passwordValidationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> tokenValidation(TokenExpiredException tokenExpiredException){
        return new ResponseEntity<String>(tokenExpiredException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsExcpetion.class)
    public ResponseEntity<String> tokenValidation(UserAlreadyExistsExcpetion userAlreadyExistsExcpetion){
        return new ResponseEntity<String>(userAlreadyExistsExcpetion.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
