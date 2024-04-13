package com.example.WeatherService.Service;


import com.example.WeatherService.Dtos.ForecastSummaryResponseDto;
import com.example.WeatherService.Exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class WeatherServiceImpl implements IWeatherService{

    @Value("${base-url}")
    private String BASE_URL;

    private RestTemplateBuilder restTemplateBuilder;
    private RedisTemplate<String,Object> redisTemplate;


    WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder,RedisTemplate<String,Object> redisTemplate){
        this.restTemplateBuilder = restTemplateBuilder;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ForecastSummaryResponseDto getForecastSummaryByLocationName(String location) {


        ForecastSummaryResponseDto forecastSummaryResponseDtoFromCache = null;
        forecastSummaryResponseDtoFromCache = (ForecastSummaryResponseDto) redisTemplate.opsForHash().get("WEATHER",location);
        if(forecastSummaryResponseDtoFromCache != null) {
            System.out.println("Read from Cache");
            return forecastSummaryResponseDtoFromCache;
        }


        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "ca9c214feemsh7547774e6b1d936p1a95ddjsn080929962d7d");
        headers.set("X-RapidAPI-Host", "forecast9.p.rapidapi.com");


        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        System.out.println("loca: " + location);
        ResponseEntity<ForecastSummaryResponseDto> responseEntity = restTemplate.exchange(
                BASE_URL + location + "/summary/",
                HttpMethod.GET,
                requestEntity,
                ForecastSummaryResponseDto.class
        );

        if(!responseEntity.hasBody()){
            throw new DataNotFoundException("Something went Wrong");
        }

        ForecastSummaryResponseDto forecastSummaryResponseDto = responseEntity.getBody();
        redisTemplate.opsForHash().put("WEATHER",location,forecastSummaryResponseDto);

        return forecastSummaryResponseDto;
    }

    @Override
    public ForecastSummaryResponseDto getForecastSummaryHourlyByLocationName(String location) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "ca9c214feemsh7547774e6b1d936p1a95ddjsn080929962d7d");
        headers.set("X-RapidAPI-Host", "forecast9.p.rapidapi.com");


        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        System.out.println("loca: " + location);
        ResponseEntity<ForecastSummaryResponseDto> responseEntity = restTemplate.exchange(
                BASE_URL + location + "/hourly/",
                HttpMethod.GET,
                requestEntity,
                ForecastSummaryResponseDto.class
        );

        if(!responseEntity.hasBody()){
            throw new DataNotFoundException("Something went Wrong");
        }

        ForecastSummaryResponseDto forecastSummaryResponseDto = responseEntity.getBody();
        return forecastSummaryResponseDto;
    }
}
