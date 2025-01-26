package com.leticia.api.services;

import com.leticia.api.domain.phone.PhoneType;
import com.leticia.api.domain.user.User;
import com.leticia.api.domain.user.UserRequestDTO;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(UserRequestDTO data){

        normalizePhoneTypes(data);
        User user = new User(
                data.getName(),
                data.getCpf(),
                data.getPassword(),
                data.isAdmin(),
                data.getAddress(),
                data.getEmail(),
                data.getPhone()
        );

        if(user.getEmail() != null && !user.getEmail().isEmpty()){
            user.getEmail().forEach(email -> email.setUser(user));
        }
        if(user.getPhone() != null && !user.getPhone().isEmpty()){
            user.getPhone().forEach(phone -> phone.setUser(user));
        }
        if(user.getAddress() != null) {
            user.getAddress().setUser(user);
        }

        return userRepository.saveAndFlush(user);
    }

    public void normalizePhoneTypes(UserRequestDTO data) {
        data.getPhone().forEach(phone ->
                phone.setType(PhoneType.valueOf(phone.getType().toString().toLowerCase())));
    }
}

