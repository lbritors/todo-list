package com.leticia.api.services;

import com.leticia.api.domain.address.Address;
import com.leticia.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


}
