package com.example.WeatherService.Dtos;

import com.example.WeatherService.Models.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private String email;
    private Set<Role> roleSet = new HashSet<>();
}