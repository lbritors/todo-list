package com.leticia.api.domain.user;

import com.leticia.api.domain.address.AddressRequestDTO;
import com.leticia.api.domain.email.EmailRequestDTO;
import com.leticia.api.domain.phone.PhoneRequestDTO;
import lombok.Getter;

import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Getter
public class UserRequestDTO {

    @NotNull
    @Size(min = 3, max = 100)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9\\s]+$")
    private  String name;

    @NotNull
    @Pattern(regexp = "^\\d{11}$")
    private  String cpf;

    @NotNull
    @Size(min = 6, max = 100)
    private  String password;


    @NotNull
    private  AddressRequestDTO address;

    @NotNull
    private  List<EmailRequestDTO> email;

    @NotNull
    private  List<PhoneRequestDTO> phone;

    @NotNull
    private  boolean admin;

    public UserRequestDTO() {}
    public UserRequestDTO(String name, String cpf, String password, AddressRequestDTO address, List<EmailRequestDTO> email, List<PhoneRequestDTO> phone, boolean admin) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.admin = admin;
    }
}