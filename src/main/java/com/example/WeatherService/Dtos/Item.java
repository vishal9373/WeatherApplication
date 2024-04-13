package com.example.WeatherService.Dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class Item implements Serializable {
    private String dateWithTimezone;
    private  Double freshSnow;
    private Double snowHeight;
    private String point;
    private String source;
    private Temprature temprature;
}
