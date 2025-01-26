package com.leticia.api.domain.phone;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PhoneResponseDTO {

    @NotNull
    @Pattern(regexp = "\\d{10,11}")
    private final String phone;

    @NotNull
    private final Long userId;

    @NotNull
    @Pattern(regexp = "comercial|residencial|celular")
    private final PhoneType type;

    @NotNull
    private final Long id;


    public PhoneResponseDTO(String phone, Long userId, PhoneType type, Long id) {
        this.phone = phone;
        this.userId = userId;
        this.type = type;
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public Long getUserId() {
        return userId;
    }

    public PhoneType getType() {
        return type;
    }

    public Long getId() {
        return id;
    }
}
