package com.example.WeatherService.Dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class Temprature implements Serializable {

    private Integer min;
    private Integer max;
    private Integer avg;
}
