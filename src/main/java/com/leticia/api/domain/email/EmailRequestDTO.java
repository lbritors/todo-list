package com.leticia.api.domain.email;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class EmailRequestDTO {


    @NotNull
    @Email
    private  String email;


    public EmailRequestDTO(){};
    public EmailRequestDTO(String email) {
        this.email = email;

    }


}
