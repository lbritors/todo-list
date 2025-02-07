package com.leticia.api.domain.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class PhoneRequestDTO {

    @NotNull
    @Pattern(regexp = "^\\d{10,11}$")
    private  String phone;

    @NotNull
    private  PhoneType type;

    @NotNull
    private UUID userId;

    public PhoneRequestDTO(String phone, PhoneType type, UUID userId) {
        this.phone = phone;
        this.type = type;
        this.userId = userId;
    }


}
