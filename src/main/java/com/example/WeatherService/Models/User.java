package com.example.WeatherService.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User extends BaseModel{

    private String email;
    private String password;

    @ManyToMany
    private Set<Role> roleSet = new HashSet<>();
}
