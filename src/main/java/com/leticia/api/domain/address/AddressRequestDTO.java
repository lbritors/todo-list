package com.leticia.api.domain.address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class AddressRequestDTO {


    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    @Pattern( regexp = "^\\d{8}$")
    private String zipCode;

    @NotNull
    private String state;

    @NotNull
    private String district;

    private String complement;


    public AddressRequestDTO(String street, String city, String zipCode, String state, String district, String complement) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.district = district;
        this.complement = complement;
    }


}
