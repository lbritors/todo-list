package com.leticia.api.domain.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmailRequestDTO {


    @NotNull
    @Email
    private final String email;



    public EmailRequestDTO(String email) {
        this.email = email;

    }


    public String getEmail() {
        return email;
    }


}
