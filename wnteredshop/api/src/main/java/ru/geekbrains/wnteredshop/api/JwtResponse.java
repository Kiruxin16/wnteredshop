package ru.geekbrains.wnteredshop.api;


public class JwtResponse {

    public JwtResponse(String token) {
        this.token = token;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
