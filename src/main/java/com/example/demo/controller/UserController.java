package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.domain.RoleToUserForm;
import com.example.demo.domain.User;
import com.example.demo.jwt.JwtConfig;
import com.example.demo.jwt.AccessTokenAndRefreshTokenResponse;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    public UserController(UserService userService, JwtConfig jwtConfig, SecretKey secretKey) {
        this.userService = userService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        final URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        final URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
        final String authorizationHeader = request.getHeader(jwtConfig.getAuthenticationHeader());
        final String authenticationHeaderPrefix = jwtConfig.getAuthenticationHeaderPrefix();
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(authenticationHeaderPrefix)) {
            throw new RuntimeException("refresh token is missing");
        }

        final String refreshToken = authorizationHeader.substring(authenticationHeaderPrefix.length());
        try {
            log.info("refresh token...");
            final Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey).build().parseClaimsJws(refreshToken);
            final Claims body = claimsJws.getBody();
            final String username = body.getSubject();
            User user = userService.getUser(username);
            final long currentTimeMillis = System.currentTimeMillis();
            final List<String> authorities = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
            String accessToken = Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("authorities", authorities)
                    .setIssuedAt(new Date(currentTimeMillis))
                    .setExpiration(new Date(getExpirationAtSeconds(currentTimeMillis, jwtConfig.getAuthenticationExpirationSeconds())))
                    .signWith(secretKey)
                    .compact();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),
                    new AccessTokenAndRefreshTokenResponse(accessToken, refreshToken, jwtConfig.getAuthenticationHeaderPrefix()));
        } catch (JwtException | IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private long getExpirationAtSeconds(final long currentTimeMillis, long duration) {
        return currentTimeMillis + TimeUnit.SECONDS.toMillis(duration);
    }
}
