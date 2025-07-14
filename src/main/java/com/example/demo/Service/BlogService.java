package com.example.demo.Service;

import com.example.demo.Model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BlogService {
    @PersistenceContext
    private EntityManager entityManager;
    private final AtomicLong generateurId = new AtomicLong(1);

    @Transactional
    public void creerDonneesExemple() {
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
        Post post = new Post(titre, contenu, auteur);
        System.out.println("Post crée : " + post);
        entityManager.persist(post);
        entityManager.flush();
        return post;
    }

    public Post creerPost(Post post) {
        if (post.getId() == null) {
            post.setId(generateurId.getAndIncrement());
        }
        if (post.getDateCreation() == null) {
            post.setDateCreation(LocalDateTime.now());
        }
        post.setDateModification(LocalDateTime.now());
        System.out.println("Post créé : " + post);
        return post;
    }

    public List<Post> obtenirTousLesPosts() {
        return entityManager.createQuery("SELECT p FROM Post p ORDER BY p.dateCreation DESC", Post.class)
                           .getResultList();
    }

    public Post obtenirPostParId(Long id) {
        return entityManager.find(Post.class, id);
    }

    @Transactional
    public Post modifierPost(Long id, String titre, String contenu, String auteur) {
        Post post = entityManager.find(Post.class, id);
        if (post != null) {
            post.setTitre(titre);
            post.setContenu(contenu);
            post.setAuteur(auteur);
            post.dateModifie();
            entityManager.merge(post);
            entityManager.flush();
            System.out.println("Post modifié : " + post);
        }
        return post;
    }

    @Transactional
    public void supprimerPost(Long id) {
        Post post = entityManager.find(Post.class, id);
        if (post != null) {
            entityManager.remove(post);
            entityManager.flush();
            System.out.println("Post supprimé avec l'ID : " + id);
        }
    }

}