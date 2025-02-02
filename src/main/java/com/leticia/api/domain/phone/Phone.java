package com.leticia.api.domain.phone;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leticia.api.domain.user.User;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Table(name = "phone")
@Entity
public class Phone {

    @Id
    @GeneratedValue
    private UUID id;

    private String phone;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Phone() {
    }

    public Phone(UUID id, String phone, PhoneType type, User user) {
        this.id = id;
        this.phone = phone;
        this.type = type;
        this.user = user;
    }


    public Phone(PhoneRequestDTO phone) {
        this.phone = phone.getPhone();
        this.type = phone.getType();
    }

    public Phone(PhoneResponseDTO phoneResponseDTO) {
        this.id = phoneResponseDTO.getId();
        this.phone = phoneResponseDTO.getPhone();
        this.type = phoneResponseDTO.getType();

    }

}
