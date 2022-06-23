package com.example.demo.student;

import com.google.common.collect.ImmutableList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean(name = "cmd1")
    public CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student alex = new Student(
                    "Alex",
                    "alex@foxmail.com",
                    LocalDate.of(2004, Month.MARCH, 15)
            );
            Student james = new Student(
                    "James",
                    "james.bond@outlook.com",
                    LocalDate.of(1993, Month.APRIL, 21)
            );
            repository.saveAll(ImmutableList.of(mariam, alex, james));
        };
    }
}
