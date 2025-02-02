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
import com.leticia.api.exceptions.UserAlreadyExistsException;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final PhoneService phoneService;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, AddressService addressService,
                       PhoneService phoneService, EmailService emailService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.phoneService = phoneService;
        this.emailService = emailService;
    }


    private EmailRequestDTO emailRequestDTO;
    @Transactional
    public User createUser(UserRequestDTO data) {

        if(userRepository.findUserByCpf(data.getCpf()) != null) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setCpf(data.getCpf());
        user.setName(data.getName());
        user.setPassword(data.getPassword());
        user.setAdmin(data.isAdmin());

        Address address = new Address(data.getAddress());
        address.setUser(user);
        user.setAddress(address);

        List<Email> emails = new ArrayList<>();
        for(EmailRequestDTO emailDto : data.getEmail()) {
            Email email = new Email(emailDto);
            email.setUser(user);
            emails.add(email);
        }

        user.setEmail(emails);

        List<Phone> phones = new ArrayList<>();
        for(PhoneRequestDTO phoneDto : data.getPhone()) {
            Phone phone = new Phone(phoneDto);
            phone.setUser(user);
            phones.add(phone);
        }

        user.setPhone(phones);


        return userRepository.save(user);
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