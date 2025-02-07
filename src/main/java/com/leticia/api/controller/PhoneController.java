package com.leticia.api.controller;

import com.leticia.api.domain.phone.PhoneRequestDTO;
import com.leticia.api.domain.phone.Phone;
import com.leticia.api.domain.phone.PhoneResponseDTO;
import com.leticia.api.repositories.PhoneRepository;
import com.leticia.api.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @PostMapping
    public ResponseEntity<Object> createPhone(@Valid @RequestBody PhoneRequestDTO data){
        try {
            Phone phone = phoneService.createPhone(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(phone);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Phone>> getAllPhone() {
        try {
            List<Phone> phones = phoneService.getAllPhones();
            return ResponseEntity.ok(phones);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phone> getPhoneById(@PathVariable UUID id) {
        try {
            Phone phone = phoneService.getPhoneById(id);
            return ResponseEntity.ok(phone);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Phone> updatePhone(@Valid @RequestBody PhoneRequestDTO data,  @PathVariable UUID id) {
        try {
            Phone phone = phoneService.updatePhone(data, id);
            return ResponseEntity.ok(phone);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePhone(@PathVariable UUID id) {

        try {
            phoneService.deletePhone(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

