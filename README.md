# WeatherApplication

End Points to Explore: 

1. http://localhost:9191/auth/signUp
payload: 
{
    "email": "vishal@gmail.com",
    "password": "1234"
}

2. http://localhost:9191/auth/login
payload: 
{
    "email": "vishal@gmail.com",
    "password": "1234"
}

3. http://localhost:9191/weather/Mumbai
4. http://localhost:9191/weather/hourly/Mumbai
5. http://localhost:9191/auth/validate
  Payload: {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJleHBpcnlEYXRlIjoxNzEyOTg1MjcyMTk5LCJjcmVhdGVkQXQiOjE3MTI5ODQ2NzIxOTksInJvbGVzIjpbXSwiZW1haWwiOiJ2aXNoYWxAZ21haWwuY29tIn0.l75jirLaDlFBgiAVYS6zTMshJ3eHXqhXexGKtbZ5Z-c",
    "id":1
}



-> Integrated 3rd Party API for getting weather Details based on location.
-> Implmented Authentication and authorization with JWT token
-> Implmented Redis caching for faster data access.
