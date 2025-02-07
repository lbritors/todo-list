package com.leticia.api.controller;

import com.leticia.api.domain.email.EmailRequestDTO;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.email.EmailResponseDTO;
import com.leticia.api.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private  EmailService emailService;

    @PostMapping
    public ResponseEntity<Object> createEmail(@Valid @RequestBody EmailRequestDTO data){

        try {
            Email email = emailService.createEmail(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Email>> getAllEmails(){
        return emailService.getAllEmails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable UUID id){

        try{
            Email email = emailService.getEmailById(id);
            return ResponseEntity.status(200).body(email);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Email> updateEmail(@Valid @RequestBody EmailRequestDTO data, @PathVariable UUID id){
        Email email = new Email();
        try{
            email = emailService.updateEmail(id, data);
            return ResponseEntity.status(200).body(email);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmail(@PathVariable UUID id){

        try{
            emailService.deleteEmail(id);
            return ResponseEntity.accepted().build();
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
