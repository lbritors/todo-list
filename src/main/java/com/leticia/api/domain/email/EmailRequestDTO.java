package com.leticia.api.domain.email;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class EmailRequestDTO {


    @NotNull
    @Email
    private  String email;

    @NotNull
    private UUID userId;


    public EmailRequestDTO(String email, UUID userId) {
        this.email = email;
        this.userId = userId;

    }


}
