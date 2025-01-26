package com.leticia.api.services;


import com.leticia.api.domain.user.CreateUserDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.domain.user.UserResponseDTO;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PhoneService phoneService;

    @Transactional
    public User createUser(CreateUserDTO data){

        User user = new User(
                data.getName(),
                data.getCpf(),
                data.getPassword(),
                data.isAdmin(),
                data.getAddress(),
                data.getEmail(),
                data.getPhone()
        );

        if(user.getAddress() != null) {
            user.getAddress().setUser(user);
        }
        if(user.getEmail() != null && !user.getEmail().isEmpty()) {
            user.getEmail().forEach(email -> email.setUser(user));
        }
        if(user.getPhone() != null && !user.getPhone().isEmpty()) {
            user.getPhone().forEach(phone -> phone.setUser(user));
        }
        return userRepository.saveAndFlush(user);
    }

}

