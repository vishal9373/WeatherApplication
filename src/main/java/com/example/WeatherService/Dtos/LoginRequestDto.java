package com.example.WeatherService.Dtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
