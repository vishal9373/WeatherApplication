package com.example.WeatherService.Exceptions;

public class PasswordValidationException extends RuntimeException{

    public PasswordValidationException(String message){
        super(message);
    }

}
