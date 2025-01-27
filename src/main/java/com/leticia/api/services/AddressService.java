package com.leticia.api.services;

import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.address.CreateAddressDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.exceptions.NotFoundException;
import com.leticia.api.repositories.AddressRepository;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public Address createAddress(CreateAddressDTO data) {
        UUID userId = data.getUserId();
        if(userId == null) {
            throw new IllegalArgumentException("User id must not be null");
        }

        User user = userRepository.findById(data.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found! id: " + data.getUserId()));


        Address address = new Address();
        address.setUser(user);
        address.setStreet(data.getStreet());
        address.setCity(data.getCity());
        address.setZipCode(data.getZipCode());
        address.setState(data.getState());
        address.setDistrict(data.getDistrict());

        if(data.getComplement() != null && !data.getComplement().isEmpty())  {
            address.setComplement(data.getComplement());
        }

        return addressRepository.saveAndFlush(address);

    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(UUID addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found! id: " + addressId));
    }

    public Address updateAddress(UUID addressId, CreateAddressDTO data) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found! id: " + addressId));
        if(data.getUserId() != null) {
            User user = userRepository.findById(data.getUserId())
                    .orElseThrow(() -> new NotFoundException("User not found! id: " + data.getUserId()));
            address.setUser(user);
        }

        if(data.getStreet() != null) address.setStreet(data.getStreet());
        if(data.getCity() != null) address.setCity(data.getCity());
        if(data.getZipCode() != null) address.setZipCode(data.getZipCode());
        if(data.getState() != null) address.setState(data.getState());
        if(data.getDistrict() != null) address.setDistrict(data.getDistrict());
        if(data.getComplement() != null) address.setComplement(data.getComplement());


        return addressRepository.saveAndFlush(address);

    }

    @Transactional
    public void deleteAddress(UUID addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found! id: " + addressId));

        addressRepository.delete(address);
    }

}
