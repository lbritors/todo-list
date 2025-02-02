package com.leticia.api.domain.address;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class AddressRequestDTO {


    @NotNull
    private final String street;

    @NotNull
    private final String city;

    @NotNull
    @Pattern( regexp = "^\\d{8}$")
    private final String zipCode;

    @NotNull
    private final String state;

    @NotNull
    private final String district;

    private final String complement;

    public AddressRequestDTO(String street, String city, String zipCode, String state, String district, String complement) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.district = district;
        this.complement = complement;
    }


}
