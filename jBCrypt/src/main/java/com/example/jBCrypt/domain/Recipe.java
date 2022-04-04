package com.example.jBCrypt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NonNull;

import javax.persistence.*;

@JsonIgnoreProperties({"customer"})
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    String textContent;

    @ManyToOne
    private Customer customer;

    public Recipe() {
    }

    public Recipe(@NonNull String textContent) {
        this.textContent = textContent;
    }

    public Long getId() {
        return id;
    }

    public String getTextContent() {
        return textContent;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "textContent='" + textContent + '\'' +
                '}';
    }
}
