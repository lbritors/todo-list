package com.leticia.api.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private String cpf;

    private String password;

    private Boolean admin;



    public User() {
    }

    public User(UUID id, String name, String cpf, String password, Boolean admin) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.admin = admin;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }


}
