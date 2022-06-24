package com.example.demo.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.server.ExportException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try (InputStream requestInputStream = request.getInputStream()) {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper().readValue(requestInputStream, UsernameAndPasswordAuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            );
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long getExpirationAtSeconds(final long currentTimeMillis, long duration) {
        return currentTimeMillis + TimeUnit.SECONDS.toMillis(duration);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        final long currentTimeMillis = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(getExpirationAtSeconds(currentTimeMillis, jwtConfig.getAuthenticationExpirationSeconds())))
                .signWith(secretKey)
                .compact();
        String refreshToken = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(getExpirationAtSeconds(currentTimeMillis, jwtConfig.getAuthenticationExpirationSeconds() * 2)))
                .signWith(secretKey)
                .compact();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(new ObjectMapper()
                    .writeValueAsString(
                            new JwtResponseObject(token, refreshToken, jwtConfig.getAuthenticationHeaderPrefix())
                    ).getBytes());
            outputStream.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
