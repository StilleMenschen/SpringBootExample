package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class ExampleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleDemoApplication.class, args);
    }

    @Bean(name = "cmd2")
    public CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "John Travolta", "john", "1234", Collections.emptyList()));
            userService.saveUser(new User(null, "Will Smith", "will", "1234", Collections.emptyList()));
            userService.saveUser(new User(null, "Jim Carry", "jim", "1234", Collections.emptyList()));
            userService.saveUser(new User(null, "Arnold Schwarzenegger", "arnold", "1234", Collections.emptyList()));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("will", "ROLE_MANAGER");
            userService.addRoleToUser("jim", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_USER");
        };
    }
}