package com.example.demo.response;

public class TokenResponse {
    private String AccessToken;

    public TokenResponse(String accessToken) {
        AccessToken = accessToken;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }
}
