package com.leticia.api.domain.user;

import javax.validation.constraints.*;
import java.util.UUID;

public class UserRequestDTO {

    @NotNull
    @Size(min = 3, max = 100)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9\\s]+$")
    private final String name;

    @NotNull
    @Pattern(regexp = "^\\d{11}$")
    private final String cpf;

    @NotNull
    @Size(min = 6, max = 100)
    private final String password;

    @NotNull
    private final UUID id;

//    @NotNull
//    @Valid
//    private final AddressRequestDTO address;
//
//    @NotNull
//    @Valid
//    private final List<EmailRequestDTO> email;
//
//    @NotNull
//    @Valid
//    private final List<PhoneRequestDTO> phone;

    @NotNull
    private final boolean admin;

    public UserRequestDTO(String name, String cpf, String password,  boolean admin) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
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

    public UUID getId() {
        return id;
    }
//    public AddressRequestDTO getAddress() {
//        return address;
//    }
//
//    public List<EmailRequestDTO> getEmail() {
//        return email;
//    }
//
//    public List<PhoneRequestDTO> getPhone() {
//        return phone;
//    }

    public boolean isAdmin() {
        return admin;
    }
}
