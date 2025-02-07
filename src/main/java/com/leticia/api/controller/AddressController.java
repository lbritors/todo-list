package com.leticia.api.controller;

import com.leticia.api.domain.address.AddressRequestDTO;
import com.leticia.api.services.AddressService;
import com.leticia.api.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import com.leticia.api.domain.address.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private  AddressService addressService;



    @PostMapping
    public ResponseEntity<Object> createAddress(@Valid @RequestBody AddressRequestDTO data){

        try{
            Address address = addressService.createAddress(data);
            return ResponseEntity.status(201) .body(address);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Address> getAllAddresses(){
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Address> getAddressById(@PathVariable UUID id){

        try{
            Address address = addressService.getAddressById(id);
            return ResponseEntity.ok(address);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody AddressRequestDTO data, @PathVariable UUID id){

        try{
            Address address = addressService.updateAddress(id, data);
            return ResponseEntity.ok(address);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable UUID id){

        try {
            Address address = addressService.deleteAddress(id);
            return ResponseEntity.ok("Address deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}
