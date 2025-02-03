package com.leticia.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.leticia.api.domain.user.AuthDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthDTO auth) throws NotFoundException, AuthenticationException {
        User user = this.userRepository.findUserByCpf(auth.getCpf());

        if(user == null) {
            throw new NotFoundException("User/password incorrect");
        }

        boolean pass = this.passwordEncoder.matches(auth.getPassword(), user.getPassword());
        if(!pass) {
            throw new AuthenticationException("User not authorized!");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withIssuer("seatech").withSubject(user.getId().toString()).sign(algorithm);

    }

}
