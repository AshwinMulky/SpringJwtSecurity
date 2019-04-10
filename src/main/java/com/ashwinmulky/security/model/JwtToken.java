package com.ashwinmulky.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtToken extends UsernamePasswordAuthenticationToken {

    private String token;

    public JwtToken(String token) {
        super(null,null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
