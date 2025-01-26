package com.leticia.api.domain.user;

import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.phone.Phone;
import lombok.Getter;

import java.util.List;


public class UserRequestDTO {

    private final String name;
    private final String cpf;
    private final String password;
    private final Address address;
    private final List<Email> email;
    private final List<Phone> phone;
    private final boolean admin;

    public UserRequestDTO(String name, String cpf, String password, Address address, List<Email> email, List<Phone> phone, boolean admin) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public List<Email> getEmail() {
        return email;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public boolean isAdmin() {
        return admin;
    }
}
