package com.example.WeatherService.Service;

import com.example.WeatherService.Models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.util.MultiValueMap;

public interface IAuthService {

    public User signUp(String email, String password) throws JsonProcessingException;

    public Pair<User, MultiValueMap<String,String>> loginIn(String email, String password);

    public boolean validateToken(String token, Long userId);


}
