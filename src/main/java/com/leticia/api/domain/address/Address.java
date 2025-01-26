package com.leticia.api.domain.address;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leticia.api.domain.user.User;
import javax.persistence.*;
import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
