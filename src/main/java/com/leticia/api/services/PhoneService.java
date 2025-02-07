package com.leticia.api.services;

import com.leticia.api.domain.phone.Phone;
import com.leticia.api.domain.phone.PhoneRequestDTO;
import com.leticia.api.domain.phone.PhoneResponseDTO;
import com.leticia.api.domain.phone.PhoneType;
import com.leticia.api.domain.user.User;
import com.leticia.api.domain.user.UserResponseDTO;
import com.leticia.api.exceptions.NotFoundException;
import com.leticia.api.repositories.PhoneRepository;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Phone createPhone(PhoneRequestDTO data) {

        User user = userRepository.findById(data.getUserId()).orElseThrow(() -> new NotFoundException("User not found!"));

        Phone phone = new Phone();
        phone.setUser(user);
        phone.setType(data.getType());
        phone.setPhone(data.getPhone());

        try{
            phone = phoneRepository.save(phone);
        }catch (Exception e){
            System.out.println(e.getMessage());

            throw new RuntimeException("Failed to save phone");
        }

        return phone;

    }

    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Phone getPhoneById(UUID id) {

        return phoneRepository.findById(id).orElseThrow(() -> new NotFoundException("Phone not found!"));

    }

    @Transactional
    public Phone updatePhone(PhoneRequestDTO data, UUID id) {
        Phone phone = phoneRepository.findById(id).orElseThrow(() -> new NotFoundException("Phone not founde") );


        if(data.getPhone() != null) phone.setPhone(data.getPhone());
        if(data.getType() != null) phone.setType(data.getType());
        return phone;
    }

    public void deletePhone(UUID id){
        Phone phone = phoneRepository.findById(id).orElseThrow(() -> new NotFoundException("Phone not found!"));

        User user = phone.getUser();
        if(user.getPhone().size() <= 1) {
            throw new IllegalStateException("User must have at least one phone");
        }

        user.getPhone().remove(phone);
        userRepository.save(user);

        phoneRepository.delete(phone);

    }

    public void normalizePhoneTypes(Phone phone) {
        phone.setType(PhoneType.valueOf(phone.getType().toString().toLowerCase()));
    }

    public void processPhones(List<Phone> phones) {
        phones.forEach(this::normalizePhoneTypes);
    }


}
