package com.leticia.api.services;

import com.leticia.api.domain.email.EmailRequestDTO;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.email.EmailResponseDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.exceptions.NotFoundException;
import com.leticia.api.repositories.EmailRepository;
import com.leticia.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    private EmailRequestDTO email;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public Email createEmail(EmailRequestDTO data, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found!"));

       Email email= new Email();
       email.setEmail(email.getEmail());
       email.setUser(user);

       try {
           email =  emailRepository.save(email);
       }catch (Exception e) {
            throw new RuntimeException("Failed to save email");
       }

       return email;
    }

    public ResponseEntity<List<EmailResponseDTO>> getAllEmails() {

    }

    public Email getEmailById(UUID emailId) {
        return emailRepository.findById(emailId)
                .orElseThrow(() -> new NotFoundException("Email not found! id: " + emailId));
    }

    public Email updateEmail(UUID emailId, EmailRequestDTO data) {
        Email email = emailRepository.getById(emailId);
        if (email.getId() == null) {
            throw new NotFoundException("Email not found! id: " + emailId);
        }
        email.setEmail(data.getEmail());
        return emailRepository.saveAndFlush(email);
    }

    public void deleteEmail(UUID emailId) {
        Email email = emailRepository.getById(emailId);
        if (email.getId() == null) {
            throw new NotFoundException("Email not found! id: " + emailId);
        }
        emailRepository.deleteById(emailId);
    }


}
