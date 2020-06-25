package tech.tystnad.works.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.tystnad.works.factory.JwtUserFactory;
import tech.tystnad.works.model.User;
import tech.tystnad.works.repository.UserRepository;

import javax.annotation.Resource;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("查询用户:{}", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
