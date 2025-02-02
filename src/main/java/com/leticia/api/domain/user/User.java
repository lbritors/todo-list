package com.leticia.api.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.phone.Phone;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String cpf;

    private String password;

    private Boolean admin;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Email> email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Phone> phone;


    public User() {
    }

    public User(String name, String cpf, String password, Boolean admin, Address address, List<Email> email, List<Phone> phone) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.admin = admin;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
    public User(UserRequestDTO userRequestDTO) {
        this.cpf = userRequestDTO.getCpf();
        this.name = userRequestDTO.getName();
        this.password = userRequestDTO.getName();
        this.admin = userRequestDTO.isAdmin();
    }

    public User(UserResponseDTO userResponseDTO){
        this.name = userResponseDTO.getName();
        this.cpf = userResponseDTO.getCpf();
        this.password = userResponseDTO.getPassword();
        this.admin = userResponseDTO.isAdmin();
    }

}
