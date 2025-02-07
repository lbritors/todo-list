package com.leticia.api.domain.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class PhoneResponseDTO {

    @NotNull
    @Pattern(regexp = "\\d{10,11}")
    private String phone;

    @NotNull
    @Pattern(regexp = "comercial|residencial|celular")
    private PhoneType type;

    @NotNull
    private  UUID id;

    private UUID userId;


    public PhoneResponseDTO(String phone, PhoneType type, UUID id, UUID userId) {
        this.phone = phone;
        this.type = type;
        this.id = id;
        this.userId = userId;
    }


    public PhoneResponseDTO(Phone phone) {
    }
}
