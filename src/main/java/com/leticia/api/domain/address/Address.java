package com.leticia.api.domain.address;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leticia.api.domain.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Table(name = "address")
@Entity
public class Address {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "zip_code")
    private String zipCode;

    private String district;

    private String street;

    private String state;

    private String city;

    private String complement;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Address() {
    }

    public Address(UUID id, String zipCode, String district, String street, String state, String city, String complement, User user) {
        this.id = id;
        this.zipCode= zipCode;
        this.district = district;
        this.street = street;
        this.state = state;
        this.city = city;
        this.complement = complement;
        this.user = user;
    }

   public Address(AddressRequestDTO addressRequestDTO) {
        this.street = addressRequestDTO.getStreet();
        this.city = addressRequestDTO.getCity();
        this.zipCode = addressRequestDTO.getZipCode();
        this.state = addressRequestDTO.getState();
        this.district = addressRequestDTO.getDistrict();
        this.complement = addressRequestDTO.getComplement();
   }

   public Address(AddressResponseDTO addressResponseDTO) {
        this.id = addressResponseDTO.getId();
        this.zipCode = addressResponseDTO.getZipCode();
        this.state = addressResponseDTO.getState();
        this.city = addressResponseDTO.getCity();
        this.district = addressResponseDTO.getDistrict();
        this.street = addressResponseDTO.getStreet();
        this.complement = addressResponseDTO.getComplement();
   }

}
