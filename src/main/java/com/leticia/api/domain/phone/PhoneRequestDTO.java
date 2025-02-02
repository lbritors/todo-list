package com.leticia.api.domain.phone;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class PhoneRequestDTO {

    @NotNull
    @Pattern(regexp = "^\\d{10,11}$")
    private  String phone;

    @NotNull
    private  PhoneType type;


    public PhoneRequestDTO(){};

    public PhoneRequestDTO(String phone, PhoneType type) {
        this.phone = phone;
        this.type = type;
    }


}
