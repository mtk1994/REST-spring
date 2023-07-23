package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner CommandLineRunner (StudentRepository repository){
        return args -> {
            // initial test data
            Student maria = new Student("Maria", "maria.m@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));
            Student alex = new Student("Alex", "alex.m@gmail.com", LocalDate.of(1990, Month.MAY, 25));

            repository.saveAll(List.of(maria, alex));
        };

    }
}
