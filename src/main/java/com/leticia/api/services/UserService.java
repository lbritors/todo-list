package com.leticia.api.services;


import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.address.CreateAddressDTO;
import com.leticia.api.domain.email.CreateEmailDTO;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.phone.CreatePhoneDTO;
import com.leticia.api.domain.phone.Phone;
import com.leticia.api.domain.user.CreateUserDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.exceptions.NotFoundException;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    @Transactional
    public User createUser(CreateUserDTO data) {
        User user = new User();
        user.setName(data.getName());
        user.setCpf(data.getCpf());
        user.setPassword(data.getPassword());
        user.setAdmin(data.isAdmin());

        user = userRepository.save(user);

        CreateAddressDTO address = data.getAddress();
        if(address != null) {
            Address addressEntity = addressService.createAddress(address, user);
            user.setAddress(addressEntity);
        }

        return user;

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new NotFoundException("User not found! id: " + userId));
    }

    public User updateUser(UUID userId, CreateUserDTO data) {
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
        userRepository.delete(user);
    }

}