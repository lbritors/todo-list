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


        User user = new User(data.getCpf(), data.getName(), data.getPassword(), data.isAdmin();



        System.out.println("USER ID !!!!!" + userId);

//        List<Email> emails = new ArrayList<>();
//        for(EmailRequestDTO email : data.getEmail()) {
//            Email emailEntity = new Email(emailRequestDTO);
//            emailEntity.setUser(user);
//            emails.add(emailEntity);
//
//        }
//        user.setEmail(emails);
//        System.out.println("emails impressos" + emails);
//
//        List<Phone> phones = new ArrayList<>();
//        for (PhoneRequestDTO phone : data.getPhone()) {
//            Phone phoneEntity = new Phone(phone);
//            phoneEntity.setUser(user);
//            phones.add(phoneEntity);
//        }
//        user.setPhone(phones);



//        if(address != null) {
//            Address addressEntity = addressService.createAddress(address, userId);
//            user.setAddress(addressEntity);
//        }


        return user;

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