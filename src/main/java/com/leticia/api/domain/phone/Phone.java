package com.leticia.api.domain.phone;

import com.leticia.api.domain.user.User;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "phone")
@Entity
public class Phone {

    @Id
    @GeneratedValue
    private UUID id;

    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Phone() {
    }

    public Phone(UUID id, String number, PhoneType type, User user) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.user = user;
    }
}
