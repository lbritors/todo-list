package com.leticia.api.security;

import com.leticia.api.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class SecurityFilter  extends OncePerRequestFilter {

    @Autowired
    JWTProvider jwtProvider;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);

        String header = request.getHeader("Authorization");
        if(header != null) {
            //String subjectToken = this.jwtProvider.validateToke(header).toString();
            Map<String, Object> claims = this.jwtProvider.validateToke(header);
            String userId = (String) claims.get("subject");
            String role = (String) claims.get("role");
           if(userId.isEmpty()) {
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
               return;
           }

            System.out.println("USER ID " + userId);
            System.out.println("ROLE " + role);

           request.setAttribute("user_id", userId);
           UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
           if(userDetails == null) {
               throw new UsernameNotFoundException("user detaisl is null for CPF: " + userId);
           }
           if(userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty()) {
               throw new RuntimeException("No authorities found for user " +userId );
           }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request,response);
    }
}
