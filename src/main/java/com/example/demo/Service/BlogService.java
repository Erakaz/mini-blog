package com.example.demo.Service;

import com.example.demo.Model.Post;
import org.springframework.stereotype.Service;

//import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BlogService {
    private final Map<Long, Post> posts = new ConcurrentHashMap<>();
    private final AtomicLong generateurId = new AtomicLong(1);

    public BlogService() {
        creerDonneesExemple();
    }

      private void creerDonneesExemple() {
        creerPost("Bienvenue sur mon blog", 
                 "Ceci est mon premier article de blog créé avec Spring Boot ! " +
                 "Nous allons apprendre ensemble les concepts du développement web.", 
                 "admin");
        
        creerPost("Les méthodes HTTP expliquées", 
                 "GET pour récupérer, POST pour créer, PUT pour modifier, DELETE pour supprimer. " +
                 "Chaque méthode a son rôle spécifique dans les API REST.", 
                 "admin");
        
        creerPost("JSON et API REST", 
                 "JSON (JavaScript Object Notation) est le format standard pour échanger des données " +
                 "entre le frontend et le backend dans les applications web modernes.", 
                 "admin");
    }

    public Post creerPost(String titre, String contenu, String auteur) {
        Long nouvelId = generateurId.getAndIncrement();
        Post post = new Post(titre, contenu, auteur);
        post.setId(nouvelId);
        posts.put(nouvelId, post);
        System.out.println("Post crée : " + post);
        return post;
    }

    
}