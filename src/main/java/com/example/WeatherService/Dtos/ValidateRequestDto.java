package com.example.WeatherService.Dtos;

import lombok.Data;

@Data
public class ValidateRequestDto {
    private String token;
    private Long id;
}
