package com.leticia.api.domain.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmailResponseDTO {

    @NotNull
    private final Long userId;

    @NotNull
    @Email
    private final String email;

    @NotNull
    private final Long id;

    public EmailResponseDTO(String email, Long userId, Long id) {
        this.email = email;
        this.userId = userId;
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}
