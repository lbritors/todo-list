package com.leticia.api.services;

import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.address.AddressRequestDTO;
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
    public Address createAddress(AddressRequestDTO data) {
        User user = userRepository.findById(data.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));

        Address address = new Address();
        address.setStreet(data.getStreet());
        address.setCity(data.getCity());
        address.setZipCode(data.getZipCode());
        address.setState(data.getState());
        address.setDistrict(data.getDistrict());

        if(data.getComplement() != null && !data.getComplement().isEmpty())  {
            address.setComplement(data.getComplement());
        }

        try{
            address = addressRepository.saveAndFlush(address);
        }catch (Exception e){
            throw new RuntimeException("Failed to save address!");
        }

        return address;

    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(UUID addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found! id: " + addressId));
    }

    @Transactional
    public Address updateAddress(UUID addressId, AddressRequestDTO data) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found! id: " + addressId));


        if(data.getStreet() != null) address.setStreet(data.getStreet());
        if(data.getCity() != null) address.setCity(data.getCity());
        if(data.getZipCode() != null) address.setZipCode(data.getZipCode());
        if(data.getState() != null) address.setState(data.getState());
        if(data.getDistrict() != null) address.setDistrict(data.getDistrict());
        if(data.getComplement() != null) address.setComplement(data.getComplement());


        return addressRepository.saveAndFlush(address);

    }

    @Transactional
    public Address deleteAddress(UUID addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found! id: " + addressId));

        addressRepository.delete(address);
        addressRepository.flush();
        return address;
    }

}
