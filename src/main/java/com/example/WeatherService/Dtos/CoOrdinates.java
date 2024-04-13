package com.example.WeatherService.Dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoOrdinates implements Serializable {

    private Double latitude;
    private Double longitude;
}
