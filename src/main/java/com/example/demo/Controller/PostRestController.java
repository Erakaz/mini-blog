package com.example.demo.Controller;

import com.example.demo.Model.Post;
import com.example.demo.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostRestController {

    private final BlogService blogService;

    @Autowired
    public PostRestController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> obtenirTousLesPosts() {
        try {
            List<Post> posts = blogService.obtenirTousLesPosts();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> obtenirPostParId(@PathVariable Long id) {
        try {
            Post post = blogService.obtenirPostParId(id);
            if (post != null) {
                return ResponseEntity.ok(post);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Post> creerPost(@RequestBody Post post) {
        try {
            if (post.getTitre() == null || post.getTitre().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (post.getContenu() == null || post.getContenu().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (post.getAuteur() == null || post.getAuteur().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            Post nouveauPost = blogService.creerPost(post.getTitre(), post.getContenu(), post.getAuteur());
            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> modifierPost(@PathVariable Long id, @RequestBody Post postModifie) {
        try {
            Post postExistant = blogService.obtenirPostParId(id);
            if (postExistant == null) {
                return ResponseEntity.notFound().build();
            }

            if (postModifie.getTitre() == null || postModifie.getTitre().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (postModifie.getContenu() == null || postModifie.getContenu().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (postModifie.getAuteur() == null || postModifie.getAuteur().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Post postMisAJour = blogService.modifierPost(id, postModifie.getTitre(), postModifie.getContenu(), postModifie.getAuteur());
            return ResponseEntity.ok(postMisAJour);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPost(@PathVariable Long id) {
        try {
            Post post = blogService.obtenirPostParId(id);
            if (post == null) {
                return ResponseEntity.notFound().build();
            }
            
            blogService.supprimerPost(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}