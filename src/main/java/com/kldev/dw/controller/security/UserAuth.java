package com.kldev.dw.controller.security;

import java.security.Principal;

public class UserAuth implements Principal {

    private String username;
    private String id;
    private String role;

    public UserAuth(String username, String id, String role) {
        this.username = username;
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
