package com.example.demo.jwt;

import com.google.common.net.HttpHeaders;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String secretKey;
    private String authenticationHeaderPrefix;
    private Long authenticationExpirationSeconds;

    public JwtConfig() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAuthenticationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

    public String getAuthenticationHeaderPrefix() {
        return authenticationHeaderPrefix;
    }

    public void setAuthenticationHeaderPrefix(String authenticationHeaderPrefix) {
        this.authenticationHeaderPrefix = authenticationHeaderPrefix;
    }

    public Long getAuthenticationExpirationSeconds() {
        return authenticationExpirationSeconds;
    }

    public void setAuthenticationExpirationSeconds(Long authenticationExpirationSeconds) {
        this.authenticationExpirationSeconds = authenticationExpirationSeconds;
    }
}
