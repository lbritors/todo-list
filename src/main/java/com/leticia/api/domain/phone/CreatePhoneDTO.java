package com.leticia.api.domain.phone;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class CreatePhoneDTO {

    @NotNull
    @Pattern(regexp = "\\d{10,11}")
    private final String phone;

    @NotNull
    private final PhoneType type;

    @NotNull
    private final UUID userId;



    public CreatePhoneDTO(String phone, PhoneType type, UUID userId) {
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

    public UUID getUserId() {
        return userId;
    }
}
