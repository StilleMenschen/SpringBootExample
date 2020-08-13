package tech.tystnad.works.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import tech.tystnad.works.model.*;
import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.model.vo.SysUserVO;
import tech.tystnad.works.service.AuthService;
import tech.tystnad.works.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    private AuthService authService;
    private UserRepository userRepository;

    @Autowired
    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
        logger.info(this.authService.toString());
    }

    @PostMapping(value = "${jwt.route.authentication.login}")
    public Auth createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final User user = userRepository.findByUsername(authenticationRequest.getUsername());
        // Return the token
        return new Auth(token, user);
    }

    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @PostMapping(value = "${jwt.route.authentication.register}")
    public ResponseObjectEntity<SysUserVO> register(@RequestBody SysUserDTO sysUserDTO) throws AuthenticationException {
        return authService.register(sysUserDTO);
    }
}
