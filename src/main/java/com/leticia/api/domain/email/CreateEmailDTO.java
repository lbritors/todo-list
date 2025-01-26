package com.leticia.api.domain.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CreateEmailDTO {

    @NotNull
    private final Long userId;

    @NotNull
    @Email
    private final String email;


    public CreateEmailDTO(String email, Long id, Long userId) {
        this.email = email;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

}
