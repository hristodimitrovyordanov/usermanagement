package com.hristo.usermanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class UserManagementSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from members where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, authority from roles where username=?");
        return jdbcUserDetailsManager;
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
}
