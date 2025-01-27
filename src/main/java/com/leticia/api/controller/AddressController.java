package com.leticia.api.controller;

import com.leticia.api.domain.address.CreateAddressDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import com.leticia.api.domain.address.Address;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping
    public List<Address> getAllAddresses(){
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public  Address getAddressById(@PathVariable UUID id){
        return addressService.getAddressById(id);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@Valid CreateAddressDTO data, @PathVariable UUID id){
        return addressService.updateAddress(id, data);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable UUID id){
        addressService.deleteAddress(id);
    }

}
