package com.leticia.api.domain.phone;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CreatePhoneDTO {

    @NotNull
    @Pattern(regexp = "\\d{10,11}")
    private final String phone;

    @NotNull
    @Pattern(regexp = "comercial|residencial|celular")
    private final PhoneType type;

    @NotNull
    private final Long userId;



    public CreatePhoneDTO(String phone, PhoneType type, Long userId) {
        this.phone = phone;
        this.type = type;
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public PhoneType getType() {
        return type;
    }

    public Long getUserId() {
        return userId;
    }
}
