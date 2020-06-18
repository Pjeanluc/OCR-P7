package com.ocr.p7.OCRP7.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class SpringSecurityTestConfiguration {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {

        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User.withUsername("user").password("password").roles("USER").build());
        userDetailsList.add(User.withUsername("admin").password("password").roles("ADMIN", "USER").build());
        return new InMemoryUserDetailsManager(userDetailsList);

    }

}
