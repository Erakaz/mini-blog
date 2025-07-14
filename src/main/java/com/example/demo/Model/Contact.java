package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String email;
    private String message;

    public Contact() {

    }

    public Contact(String nom, String email, String message) {
        this.nom = nom;
        this.email = email;
        this.message = message;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
