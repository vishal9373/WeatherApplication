package com.example.WeatherService.Models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Role extends BaseModel {

    private String value;

}
