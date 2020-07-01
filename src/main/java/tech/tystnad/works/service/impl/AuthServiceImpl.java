package tech.tystnad.works.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.tystnad.works.model.JwtUser;
import tech.tystnad.works.model.User;
import tech.tystnad.works.repository.UserRepository;
import tech.tystnad.works.service.AuthService;
import tech.tystnad.works.util.JwtTokenUtil;

import java.util.Collections;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private AuthenticationManager authenticationManager;
  private UserDetailsService userDetailsService;
  private JwtTokenUtil jwtTokenUtil;
  private UserRepository userRepository;

  @Value("${jwt.tokenHead}")
  private String tokenHead;

  @Autowired
  public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
      JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userRepository = userRepository;
  }

  @Override
  public User register(User userToAdd) {
    final String username = userToAdd.getUsername();
    if (userRepository.findByUsername(username) != null) {
      return new User();
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    final String rawPassword = userToAdd.getPassword();
    userToAdd.setPassword(encoder.encode(rawPassword));
    userToAdd.setLastPasswordResetDate(new Date());
    userToAdd.setRoles(Collections.singletonList("ROLE_USER"));
    return userRepository.insert(userToAdd);
  }

  @Override
  public String login(String username, String password) {
    UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
    // 进行安全认证
    logger.debug("开始验证");
    final Authentication authentication = authenticationManager.authenticate(upToken);
    logger.debug("设置权限上下文");
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // 生成token
    final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return jwtTokenUtil.generateToken(userDetails);
  }

  @Override
  public String refresh(String oldToken) {
    final String token = oldToken.substring(tokenHead.length());
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
    if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      return jwtTokenUtil.refreshToken(token);
    }
    return null;
  }
}
