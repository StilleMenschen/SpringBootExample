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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthenticationFilter.class);

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
            log.debug("valid username {}", authenticationRequest.getUsername());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            );
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private long getExpirationAtSeconds(final long currentTimeMillis, long duration) {
        return currentTimeMillis + TimeUnit.SECONDS.toMillis(duration);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        log.debug("successful valid username {}", authResult.getName());
        final long currentTimeMillis = System.currentTimeMillis();
        final List<String> authorities = authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String accessToken = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authorities)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(getExpirationAtSeconds(currentTimeMillis, jwtConfig.getAuthenticationExpirationSeconds())))
                .signWith(secretKey)
                .compact();
        String refreshToken = Jwts.builder()
                .setSubject(authResult.getName())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(getExpirationAtSeconds(currentTimeMillis, jwtConfig.getAuthenticationExpirationSeconds() * 2)))
                .signWith(secretKey)
                .compact();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            new ObjectMapper().writeValue(response.getOutputStream(),
                    new AccessTokenAndRefreshTokenResponse(accessToken, refreshToken, jwtConfig.getAuthenticationHeaderPrefix()));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
