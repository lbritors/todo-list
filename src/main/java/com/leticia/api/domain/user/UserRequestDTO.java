package com.leticia.api.domain.user;

import lombok.Getter;

import javax.validation.constraints.*;
import java.util.UUID;

@Getter
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

    public UserRequestDTO(String name, String cpf, String password,  boolean admin, UUID id) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.admin = admin;
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

}
