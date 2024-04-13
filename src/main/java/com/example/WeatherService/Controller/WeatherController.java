package com.example.WeatherService.Controller;


import com.example.WeatherService.Dtos.Response;
import com.example.WeatherService.Service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/weather")
@RestController
public class WeatherController {

    @Autowired
    private IWeatherService weatherService;

    @GetMapping("/{location}")
    public ResponseEntity<?> getWeatherDetailsByLocation(@PathVariable String location) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .data(weatherService.getForecastSummaryByLocationName(location))
                        .message("Data Fetched SuccessFully")
                        .build());

    }

    @GetMapping("hourly/{location}")
    public ResponseEntity<?> getWeatherDetailsHourlyByLocation(@PathVariable String location){
        return ResponseEntity.ok(
                Response.builder()
                        .data(weatherService.getForecastSummaryHourlyByLocationName(location))
                        .message("Data Fetched SuccessFully")
                        .build());

    }


}
