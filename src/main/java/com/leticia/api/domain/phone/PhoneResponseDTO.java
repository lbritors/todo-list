package com.leticia.api.domain.phone;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
public class PhoneResponseDTO {

    @NotNull
    @Pattern(regexp = "\\d{10,11}")
    private final String phone;

    @NotNull
    private final UUID userId;

    @NotNull
    @Pattern(regexp = "comercial|residencial|celular")
    private final PhoneType type;

    @NotNull
    private final UUID id;


    public PhoneResponseDTO(String phone, UUID userId, PhoneType type, UUID id) {
        this.phone = phone;
        this.userId = userId;
        this.type = type;
        this.id = id;
    }

}
