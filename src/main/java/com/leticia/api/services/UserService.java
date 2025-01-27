package com.leticia.api.services;


import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.address.CreateAddressDTO;
import com.leticia.api.domain.email.CreateEmailDTO;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.phone.CreatePhoneDTO;
import com.leticia.api.domain.phone.Phone;
import com.leticia.api.domain.user.CreateUserDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    private Address convertToEntity(CreateAddressDTO dto) {
        return new Address(dto.getCity(), dto.getState(), dto.getStreet(), dto.getDistrict(), dto.getZipCode());
    }

    private Email convertToEntity(CreateEmailDTO dto) {
        return new Email(dto.getEmail());
    }

    private Phone convertToEntity(CreatePhoneDTO dto) {
        return new Phone(dto.getPhone(), dto.getType());
    }


    @Transactional
    public User createUser(CreateUserDTO data) {
        Address addressEntity = convertToEntity(data.getAddress());

        // Converter lista de EmailDTO para lista de entidades Email
        List<Email> emailEntities = data.getEmail().stream()
                .map(emailDTO -> convertToEntity(emailDTO))
                .collect(Collectors.toList());

        // Converter lista de PhoneDTO para lista de entidades Phone
        List<Phone> phoneEntities = data.getPhone().stream()
                .map(phoneDTO -> convertToEntity(phoneDTO))
                .collect(Collectors.toList());
        User user = new User(
                data.getName(),
                data.getCpf(),
                data.getPassword(),
                data.isAdmin(),
                addressEntity,
                emailEntities,
                phoneEntities
        );

        if (user.getAddress() != null) {
            user.getAddress().setUser(user);
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                user.getEmail().forEach(email -> email.setUser(user));
            }
            if (user.getPhone() != null && !user.getPhone().isEmpty()) {
                user.getPhone().forEach(phone -> phone.setUser(user));
            }
        }
        return userRepository.saveAndFlush(user);

    }


}