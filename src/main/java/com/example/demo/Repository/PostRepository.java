package com.example.demo.Repository;

import com.example.demo.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitreContaining(String titre);
    List<Post> findAllByAuteurContaining(String auteur);
    List<Post> findAllByDateCreation(LocalDateTime dateCreation);
}
