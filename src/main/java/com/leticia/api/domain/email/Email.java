package com.leticia.api.domain.email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leticia.api.domain.user.User;


import javax.persistence.*;
import java.util.UUID;

@Table(name = "email")
@Entity
public class Email {

    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Email() {
    }

    public Email(UUID id, String email, User user) {
        this.id = id;
        this.email = email;
        this.user = user;
    }


    public Email(EmailRequestDTO emailRequestDTO) {
        this.email = emailRequestDTO.getEmail();
    }

    public Email(EmailResponseDTO emailResponseDTO){
        this.email = emailResponseDTO.getEmail();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
