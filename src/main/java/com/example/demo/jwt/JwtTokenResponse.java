package com.example.demo.jwt;

public class JwtTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String type;

    public JwtTokenResponse(String accessToken, String refreshToken, String type) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
