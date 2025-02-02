package com.leticia.api.services;


import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.address.AddressRequestDTO;
import com.leticia.api.domain.email.EmailRequestDTO;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.phone.PhoneRequestDTO;
import com.leticia.api.domain.phone.Phone;
import com.leticia.api.domain.user.UserRequestDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.domain.user.UserResponseDTO;
import com.leticia.api.exceptions.NotFoundException;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private EmailService emailService;


    private EmailRequestDTO emailRequestDTO;
    @Transactional
    public User createUser(UserRequestDTO data) {
            return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new NotFoundException("User not found! id: " + userId));
    }

    public User updateUser(UserRequestDTO data, UUID userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new NotFoundException("User not found! id: " + userId));

        if(data.getName() != null) user.setName(data.getName());
        if(data.getCpf() != null) user.setCpf(data.getCpf());
        if(data.getPassword() != null) user.setPassword(data.getPassword());
        if(data.isAdmin()) user.setAdmin(data.isAdmin());

        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new NotFoundException("User not found! id: " + userId));
        userRepository.deleteById(userId);
    }

}