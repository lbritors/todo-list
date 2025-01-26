package com.leticia.api.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.phone.Phone;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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
    public User(UUID id, String name, String cpf, String password, Boolean admin, Address address, List<Email> email, List<Phone> phone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.admin = admin;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Email> getEmail() {
        return email;
    }

    public void setEmail(List<Email> email) {
        this.email = email;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }
}
