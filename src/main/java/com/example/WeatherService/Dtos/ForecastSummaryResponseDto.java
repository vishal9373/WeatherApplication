package com.example.WeatherService.Dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ForecastSummaryResponseDto implements Serializable {

    private Location location;
    private ForeCast forecast;
}
