package com.leticia.api.controller;

import com.leticia.api.domain.email.EmailRequestDTO;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.email.EmailResponseDTO;
import com.leticia.api.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Email> createEmail(@Valid @RequestBody EmailRequestDTO data, UUID userId){
        Email email = emailService.createEmail(data, userId);
        return ResponseEntity.status(201).  body(email);
    }

    @GetMapping
    public ResponseEntity<List<EmailResponseDTO>> getAllEmails(){
        return emailService.getAllEmails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable UUID id){

        try{
            Email email = emailService.getEmailById(id);
            return ResponseEntity.status(200).body(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Email> updateEmail(@Valid @RequestBody EmailRequestDTO data, @PathVariable UUID id){
        Email email = new Email();
        try{
            email = emailService.updateEmail(id, data);
            return ResponseEntity.status(200).body(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmail(@PathVariable UUID id){

        try{
            emailService.deleteEmail(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
