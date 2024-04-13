package com.example.WeatherService.Controller;


import com.example.WeatherService.Dtos.*;
import com.example.WeatherService.Models.User;
import com.example.WeatherService.Service.IAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.WeatherService.Utils.UserDetailsUtils.getUserDto;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<?> singUp(@RequestBody SignUpRequestDto signUpRequestDto) throws JsonProcessingException, JsonProcessingException {

        User user = authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        UserDto userDto = getUserDto(user);

        return ResponseEntity.ok(Response.builder().data(userDto).message("User Created Successfully").build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){

        Pair<User, MultiValueMap<String,String>> pair = authService.loginIn(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        UserDto userDto = getUserDto(pair.a);
        return ResponseEntity.ok(Response.builder().data(userDto).message("Logged in Successfully").build());

        //return new ResponseEntity<>(userDto,pair.b,HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody ValidateRequestDto validateRequestDto) {
        Boolean isValid = authService.validateToken(validateRequestDto.getToken(), validateRequestDto.getId());
        return ResponseEntity.ok(Response.builder().data(isValid).message("validate Successfully").build());

      //  return new ResponseEntity<>(isValid,HttpStatus.OK);
    }
}
