package com.sarath.init.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            var sarath = Student.builder()
                    .name("Sarath")
                    .dob(LocalDate.of(2004, Month.JULY, 23))
                    .email("vssarathc04@gmail.com")
                    .build();

            var alex = Student.builder()
                    .name("alex")
                    .dob(LocalDate.of(2004, Month.JULY, 20))
                    .email("alexc04@gmail.com")
                    .build();

            repository.saveAll(
                    List.of(sarath, alex));
        };
    }
}
