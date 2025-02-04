package com.leticia.api.domain.email;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
@Getter
@NoArgsConstructor
public class EmailRequestDTO {


    @NotNull
    @Email
    private  String email;


    public EmailRequestDTO(String email) {
        this.email = email;

    }


}
