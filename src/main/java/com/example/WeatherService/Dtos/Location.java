package com.example.WeatherService.Dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class Location implements Serializable {

    private String code;

    private String timezone;
    private String name;
    private CoOrdinates coordinates;

}
