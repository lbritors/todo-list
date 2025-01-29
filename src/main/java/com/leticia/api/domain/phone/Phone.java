package com.leticia.api.domain.phone;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leticia.api.domain.user.User;


import javax.persistence.*;
import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
