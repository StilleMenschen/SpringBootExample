package tech.tystnad.works.controller;

import org.springframework.web.bind.annotation.*;
import tech.tystnad.works.model.Auth;
import tech.tystnad.works.service.AuthService;
import tech.tystnad.works.model.JwtAuthenticationRequest;
import tech.tystnad.works.model.JwtAuthenticationResponse;
import tech.tystnad.works.model.User;
import tech.tystnad.works.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private AuthService authService;
    private UserRepository userRepository;

    @Autowired
    public AuthController(AuthService authService, UserRepository userRepository){
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "${jwt.route.authentication.login}")
    public Auth createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final User user = userRepository.findByUsername(authenticationRequest.getUsername());
        // Return the token
        return new Auth(token, user);
    }

    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @PostMapping(value = "${jwt.route.authentication.register}")
    public User register(@RequestBody User addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }
}
