package com.kldev.dw.storage;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "traders")
public class Trader {

    @Id
    private String id;

    @Column(unique = true)
    private String login;

    private String password;

    private long phone;

    private String name;

    @Column(unique = true)
    private String email;

    public void setId(String id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
