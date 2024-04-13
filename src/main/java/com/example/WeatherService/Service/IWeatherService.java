package com.example.WeatherService.Service;

import com.example.WeatherService.Dtos.ForecastSummaryResponseDto;

public interface IWeatherService {

    public ForecastSummaryResponseDto getForecastSummaryByLocationName(String location);

    public ForecastSummaryResponseDto getForecastSummaryHourlyByLocationName(String location);

}
