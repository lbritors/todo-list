package com.leticia.api.security;


import com.leticia.api.domain.user.User;
import com.leticia.api.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Configuration
public class SecurityConfig {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    UserDetailsService userDetailsService() {
        return id -> {
            User user = userRepository.findById(UUID.fromString(id)).orElse(null);
            if(user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getId().toString())
                    .password(user.getPassword())
                    .authorities(new SimpleGrantedAuthority(user.getRole())).build();

        };
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                        .and()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.antMatchers(HttpMethod.POST, "/auth/user").permitAll();
                    auth.antMatchers(HttpMethod.POST, "/user").permitAll();
                    auth.antMatchers(HttpMethod.GET,"/user/**").hasAnyRole("USER", "ADMIN");
                    auth.antMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN");

                    auth.antMatchers(HttpMethod.GET, "/address/**").hasAnyRole("USER", "ADMIN");
                    auth.antMatchers(HttpMethod.POST, "/address").hasAnyRole("ADMIN");
                    auth.antMatchers(HttpMethod.PUT, "/address/**").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.DELETE, "/address/**").hasRole("ADMIN");

                    auth.antMatchers(HttpMethod.GET, "/email/**").hasAnyRole("USER", "ADMIN");
                    auth.antMatchers(HttpMethod.POST, "/email").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.PUT, "/email/**").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.DELETE, "/email/**").hasRole("ADMIN");

                    auth.antMatchers(HttpMethod.GET, "/phone/**").hasAnyRole("USER", "ADMIN");
                    auth.antMatchers(HttpMethod.POST, "/phone").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.PUT, "/phone/**").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.DELETE, "/phone/**").hasRole("ADMIN");

                })
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

}
