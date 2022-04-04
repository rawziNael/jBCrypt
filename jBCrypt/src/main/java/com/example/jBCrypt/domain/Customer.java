package com.example.jBCrypt.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String username;
    public String password;


    @OneToMany(mappedBy="customer")
    public List<Recipe> recipes;

    public Customer() {
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "ApplicationUser{" +
                "username='" + username + '\'' +
                '}';
    }
}
