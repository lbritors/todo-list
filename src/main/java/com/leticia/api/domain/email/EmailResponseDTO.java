package com.leticia.api.domain.email;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class EmailResponseDTO {

    @NotNull
    private final UUID userId;

    @NotNull
    @Email
    private final String email;

    @NotNull
    private final UUID id;

    public EmailResponseDTO(String email,  UUID id, UUID userId) {
        this.email = email;
        this.id = id;
        this.userId = userId;
    }

}
