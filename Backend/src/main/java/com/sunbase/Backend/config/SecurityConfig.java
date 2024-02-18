package com.sunbase.Backend.config;

import com.sunbase.Backend.security.JWTAuthenticationEntryPoint;
import com.sunbase.Backend.security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private JWTAuthenticationEntryPoint point;
    private JWTAuthenticationFilter filter;

    @Autowired
    public SecurityConfig(JWTAuthenticationEntryPoint point, JWTAuthenticationFilter filter) {
        this.point = point;
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        //You have to do configuration..
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/home/**").authenticated()
                        .requestMatchers("/sunbase/**").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers(("/user/**")).authenticated()
                        .anyRequest().authenticated())
                .exceptionHandling(exc->exc.authenticationEntryPoint(point))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}