package com.leticia.api.security;


import com.leticia.api.domain.user.User;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Autowired
    UserRepository userRepository;

    @Bean
    UserDetailsService userDetailsService() {
        return cpf -> {
            User user = userRepository.findUserByCpf(cpf);
            if(user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getCpf())
                    .password(user.getPassword())
                    .roles(user.getRole()).build();

        };
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.antMatchers(HttpMethod.POST, "/auth/users").permitAll();
                    auth.antMatchers(HttpMethod.POST, "/users").permitAll();
                    auth.antMatchers(HttpMethod.GET,"/users/**").hasAnyRole("USER", "ADMIN");
                    auth.antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN");

                }).httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
