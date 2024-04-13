package com.example.WeatherService.Dtos;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ForeCast implements Serializable {

    private String forecastDate;
    private String nextUpdate;
    private String source;
    private List<Item> items;
    private String point;
}
