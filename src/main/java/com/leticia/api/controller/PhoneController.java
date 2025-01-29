package com.leticia.api.controller;

import com.leticia.api.domain.phone.PhoneRequestDTO;
import com.leticia.api.domain.phone.Phone;
import com.leticia.api.domain.phone.PhoneResponseDTO;
import com.leticia.api.services.PhoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    private PhoneService phoneService;

    @PostMapping
    public Phone createPhone(@Valid @RequestBody PhoneRequestDTO data, UUID userId){
        return phoneService.createPhone(data, userId);
    }

    @GetMapping
    public ResponseEntity<List<Phone>> getAllPhone() {
        List<Phone> phones = phoneService.getAllPhones();
        return ResponseEntity.ok(phones);
    }

    @GetMapping("/id")
    public ResponseEntity<Phone> getPhoneById(@PathVariable UUID id) {
        Phone phone = phoneService.getPhoneById(id);
        return ResponseEntity.ok(phone);
    }

    @PutMapping("/id")
    public ResponseEntity<Phone> updatePhone(@Valid @RequestBody PhoneRequestDTO data,  @PathVariable UUID id) {
        Phone phone = phoneService.updatePhone(data, id);
        return ResponseEntity.ok(phone);
    }
    @DeleteMapping("/id")
    public void deletePhone(@PathVariable UUID id) {
        phoneService.deletePhone(id);
    }

}

