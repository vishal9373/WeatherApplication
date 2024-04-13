package com.example.WeatherService.Utils;


import com.example.WeatherService.Dtos.UserDto;
import com.example.WeatherService.Models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDetailsUtils {

    public static UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoleSet(user.getRoleSet());
        return userDto;
    }
}
