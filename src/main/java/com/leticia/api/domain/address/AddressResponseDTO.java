package com.leticia.api.domain.address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddressResponseDTO {

    @NotNull
    private final Long id;

    @NotNull
    private final Long userId;

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

    public AddressResponseDTO(Long id, Long userId, String street, String city, String zipCode, String state, String district, String complement) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.district = district;
        this.complement = complement;
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public String getComplement() {
        return complement;
    }
}
