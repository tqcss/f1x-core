//package com.app.f1x.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity
//                .formLogin(httpForm -> {
//                    httpForm.loginPage("/req/login").permitAll();
//                    httpForm.defaultSuccessUrl("/index");
//                })
//                .authorizeHttpRequests(registry -> {
//                    registry.requestMatchers("/req/**","/css/**","/js/**").permitAll();
//                    registry.anyRequest().authenticated();
//                })
//                .build();
//    }
//}