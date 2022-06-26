package com.example.demo.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class JwtTokenVerifierFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenVerifierFilter.class);

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtTokenVerifierFilter(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(jwtConfig.getAuthenticationHeader());
        final String authenticationHeaderPrefix = jwtConfig.getAuthenticationHeaderPrefix();

        if (request.getServletPath().startsWith("/api/token/refresh") || Strings.isNullOrEmpty(authorizationHeader)
                || !authorizationHeader.startsWith(authenticationHeaderPrefix)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.substring(authenticationHeaderPrefix.length());
        try {
            log.info("Try to verify token...");
            final Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey).build().parseClaimsJws(token);
            final Claims body = claimsJws.getBody();
            final List<String> authorities = (List<String>) body.get("authorities");
            Optional.ofNullable(authorities).ifPresent(e -> {
                final String username = body.getSubject();
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        } catch (JwtException e) {
            log.error(e.getMessage(), e);
        }
        filterChain.doFilter(request, response);
    }
}
