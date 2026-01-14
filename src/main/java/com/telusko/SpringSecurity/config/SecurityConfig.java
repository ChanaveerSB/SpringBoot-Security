package com.telusko.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity //don't go with default flow go with below flow
public class SecurityConfig {
    //bean for security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  //HttpSecurity gives SecurityFilterChain object
        //http.build() gives SecurityFilterChain
//        return http.build();

        //Adding Security Layers :

        //Disables CSRF protection, usually done for REST APIs where CSRF tokens are not required (e.g., Postman/JWT).
        http.csrf(customizer -> customizer.disable());
        //Ensures that every endpoint can be accessed only by authenticated users; no public URLs are allowed.
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        //Enables default form-based login provided by Spring Security (auto login page, username/password authentication).
        http.formLogin(Customizer.withDefaults());
        //Enables HTTP Basic authentication, where credentials are sent in the request header (commonly used for APIs and testing)
        http.httpBasic(Customizer.withDefaults());


        return http.build();
    }


}
