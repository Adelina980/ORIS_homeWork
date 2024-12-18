package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String email;
    private String password;

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
