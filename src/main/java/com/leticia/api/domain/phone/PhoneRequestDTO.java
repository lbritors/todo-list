package com.leticia.api.domain.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class PhoneRequestDTO {

    @NotNull
    @Pattern(regexp = "^\\d{10,11}$")
    private  String phone;

    @NotNull
    private  PhoneType type;


    public PhoneRequestDTO(String phone, PhoneType type) {
        this.phone = phone;
        this.type = type;
    }


}
