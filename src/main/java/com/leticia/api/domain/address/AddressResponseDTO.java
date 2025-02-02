package com.leticia.api.domain.address;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
public class AddressResponseDTO {

    @NotNull
    private final UUID id;

    @NotNull
    private final UUID userId;

    @NotNull
    private final String street;

    @NotNull
    private final String city;

    @NotNull
    @Pattern( regexp = "\\d{8}")
    private final String zipCode;

    @NotNull
    private final String state;

    @NotNull
    private final String district;

    private final String complement;

    public AddressResponseDTO(UUID id, UUID userId, String street, String city, String zipCode, String state, String district, String complement) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.district = district;
        this.complement = complement;
        this.id = id;
        this.userId = userId;
    }

}
