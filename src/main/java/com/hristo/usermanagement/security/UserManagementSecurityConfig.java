package com.hristo.usermanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class UserManagementSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/users").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/users").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                        .requestMatchers("/swagger-ui/index.html", "/swagger-ui/index.html/**", "/api-docs")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    //    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails ivan = User.builder()
//                .username("ivan")
//                .password("{noop}test1234")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails george = User.builder()
//                .username("george")
//                .password("{noop}test1234")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails hristo = User.builder()
//                .username("hristo")
//                .password("{noop}test1234")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(ivan, george, hristo);
//    }

}
