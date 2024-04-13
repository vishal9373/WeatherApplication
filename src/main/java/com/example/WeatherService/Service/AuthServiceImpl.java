package com.example.WeatherService.Service;

import com.example.WeatherService.Exceptions.PasswordValidationException;
import com.example.WeatherService.Exceptions.TokenExpiredException;
import com.example.WeatherService.Exceptions.UserAlreadyExistsExcpetion;
import com.example.WeatherService.Exceptions.UserNotFoundException;
import com.example.WeatherService.Models.Session;
import com.example.WeatherService.Models.SessionStatus;
import com.example.WeatherService.Models.User;
import com.example.WeatherService.Repositories.SessionRepository;
import com.example.WeatherService.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.antlr.v4.runtime.misc.Pair;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;


@Service
public class AuthServiceImpl implements  IAuthService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecretKey secret;

    @Autowired
    private ObjectMapper objectMapper;

    public User signUp(String email, String password) throws JsonProcessingException {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsExcpetion("User Already Exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User saveedUser = userRepository.save(user);
        return saveedUser;
    }


    public Pair<User, MultiValueMap<String,String>> loginIn(String email, String password){

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User Not Found");
        }

        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new PasswordValidationException("Password does not Matches");
        }


        HashMap<String,Object> jwtData = new HashMap<>();
        jwtData.put("email",user.getEmail());
        jwtData.put("roles",user.getRoleSet());
        long nowInMillis = System.currentTimeMillis();
        long expiryTimeInMillis = nowInMillis + (10 * 60 * 1000);
        jwtData.put("expiryDate",new Date(expiryTimeInMillis));
        jwtData.put("createdAt",new Date(nowInMillis));


        String token = Jwts.builder().claims(jwtData).signWith(secret).compact();

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setUser(user);
        session.setToken(token);
        session.setExpiringAt(new Date(expiryTimeInMillis));

        sessionRepository.save(session);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE,token);

        return new Pair<User,MultiValueMap<String,String>>(user,headers);
    }

    public boolean validateToken(String token, Long id) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token,id);

        if(optionalSession.isEmpty()) {
            System.out.println("No Token or User found");
            return false;
        }

        Session session = optionalSession.get();
        String storedToken = session.getToken();

        JwtParser jwtParser = Jwts.parser().verifyWith(secret).build();
        Claims claims = jwtParser.parseSignedClaims(storedToken).getPayload();
        System.out.println(claims);

        long nowInMillis = System.currentTimeMillis();
        long tokenExpiry = (Long)claims.get("expiryTime");

        if(nowInMillis > tokenExpiry) {
            System.out.println(nowInMillis);
            System.out.println(tokenExpiry);
            System.out.println("Token has expired");
            return false;
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) {
            return false;
        }

        String email = optionalUser.get().getEmail();

        if(!email.equals(claims.get("email"))) {
            System.out.println(email);
            System.out.println(claims.get("email"));
            System.out.println("User doesn't match");
            return false;
        }

        return true;
    }

}
