package com.stg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
@EnableScheduling
// @EnableOAuth2Sso
public class OnboardingApplication {

    public static void main(String[] args) {
	SpringApplication.run(OnboardingApplication.class, args);
    }
}