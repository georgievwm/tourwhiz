package org.georgiev.tourwhiz.security;

import org.georgiev.tourwhiz.security.filters.JWTAuthenticationFilter;
import org.georgiev.tourwhiz.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private AuthenticationService authenticationService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
        return new JWTAuthenticationFilter(authenticationService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.POST, "/users/signup").permitAll()
                                .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
                                .requestMatchers("/actuator/**").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter(authenticationService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
