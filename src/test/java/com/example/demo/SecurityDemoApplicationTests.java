package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SecurityDemoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(TimeUnit.HOURS.toSeconds(24));
    }

}
