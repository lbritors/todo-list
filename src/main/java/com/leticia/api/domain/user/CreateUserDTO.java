package com.leticia.api.domain.user;

import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.address.CreateAddressDTO;
import com.leticia.api.domain.email.CreateEmailDTO;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.phone.CreatePhoneDTO;
import com.leticia.api.domain.phone.Phone;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class CreateUserDTO {

    @NotNull
    @Size(min = 3, max = 100)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9\\s]+$")
    private final String name;

    @NotNull
    @Pattern(regexp = "^\\d{11}$")
    @CPF()
    private final String cpf;

    @NotNull
    @Size(min = 6, max = 100)
    private final String password;

    @NotNull
    @Valid
    private final CreateAddressDTO address;

    @NotNull
    @Valid
    private final List<CreateEmailDTO> email;

    @NotNull
    @Valid
    private final List<CreatePhoneDTO> phone;

    @NotNull
    private final boolean admin;

    public CreateUserDTO(String name, String cpf, String password, CreateAddressDTO address, List<CreateEmailDTO> email, List<CreatePhoneDTO> phone, boolean admin) {
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

    public CreateAddressDTO getAddress() {
        return address;
    }

    public List<CreateEmailDTO> getEmail() {
        return email;
    }

    public List<CreatePhoneDTO> getPhone() {
        return phone;
    }

    public boolean isAdmin() {
        return admin;
    }
}
