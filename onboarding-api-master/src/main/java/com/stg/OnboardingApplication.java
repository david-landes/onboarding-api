package com.stg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
	org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
public class OnboardingApplication {

    public static void main(String[] args) {
	SpringApplication.run(OnboardingApplication.class, args);
    }
}
