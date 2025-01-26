package com.leticia.api.services;

import com.leticia.api.domain.email.Email;
import com.leticia.api.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

}
