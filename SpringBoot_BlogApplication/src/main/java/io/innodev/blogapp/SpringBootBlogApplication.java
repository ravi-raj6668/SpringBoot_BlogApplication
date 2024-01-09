package io.innodev.blogapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
@Slf4j
@SpringBootApplication
public class SpringBootBlogApplication implements CommandLineRunner {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SpringBootBlogApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBlogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		log.info("Result {} value : ",passwordEncoder.encode(""));
    }
}
