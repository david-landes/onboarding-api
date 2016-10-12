package com.stg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
@EnableScheduling
//@EnableOAuth2Sso
public class OnboardingApplication /*extends WebSecurityConfigurerAdapter*/ {

    public static void main(String[] args) {
	SpringApplication.run(OnboardingApplication.class, args);
    }

    /*@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.antMatcher("/**").authorizeRequests().antMatchers("/", "/oauth2callback**", "/webjars/**").permitAll().anyRequest().authenticated();
    }*/
}