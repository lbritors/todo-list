package com.leticia.api.domain.email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leticia.api.domain.user.User;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
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

}
