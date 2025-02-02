package com.leticia.api.domain.user;

import com.leticia.api.domain.address.Address;
import com.leticia.api.domain.email.Email;
import com.leticia.api.domain.phone.Phone;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Getter
public class UserResponseDTO {

    @NotNull
    private final UUID id;

    @NotNull
    @Min(3)
    @Max(100)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9\\s]+$")
    private final String name;

    @NotNull
    @Pattern(regexp = "\\d{11}")
    private final String cpf;

    @NotNull
    @Min(6)
    private final String password;

    @NotNull
    private final Address address;

    @NotNull
    private final List<Email> email;

    @NotNull
    private final List<Phone> phone;

    @NotNull
    private final boolean admin;

    public UserResponseDTO(UUID id, String name, String cpf, String password, Address address, List<Email> email, List<Phone> phone, boolean admin) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.admin = admin;
        this.id = id;
    }

}
