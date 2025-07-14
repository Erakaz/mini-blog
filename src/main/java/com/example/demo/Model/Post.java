package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String auteur;
    private String titre;
    private String contenu;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;

    public Post() {

    }

    public Post(String titre, String contenu, String auteur) {
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
        this.dateCreation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
    }

    public Post(Long id, String titre, String contenu, String auteur, LocalDateTime dateCreation, LocalDateTime dateModification) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getContenu() {
        return contenu;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public String getAuteur() {
        return auteur;
    }
    
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    public LocalDateTime getDateModification() {
        return dateModification;
    }
    
    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public void dateModifie() {
        this.dateModification = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Post{" + "id : " + id + ", titre : " + titre + ", auteur : " + auteur + "Date de creation : " + dateCreation + "}";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) 
            return true;
        if (object == null || getClass() != object.getClass()) 
            return false;
        Post post = (Post) object;
        return id != null && id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}