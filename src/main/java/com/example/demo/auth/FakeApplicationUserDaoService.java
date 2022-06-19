package com.example.demo.auth;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> applicationUser.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return ImmutableList.of(
                new ApplicationUser(STUDENT.getGrantedAuthorities(),
                        "annasmith", passwordEncoder.encode("pwd123"),
                        Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE),
                new ApplicationUser(ADMIN.getGrantedAuthorities(),
                        "linda", passwordEncoder.encode("pwd1234"),
                        Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE),
                new ApplicationUser(ADMIN_TRAINEE.getGrantedAuthorities(),
                        "tom", passwordEncoder.encode("pwd1234"),
                        Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE)
        );
    }
}
