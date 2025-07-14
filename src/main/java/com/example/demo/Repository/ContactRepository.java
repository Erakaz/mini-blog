package com.example.demo.Repository;

import com.example.demo.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findAllByEmail(String email);
    List<Contact> findAllByNomContaining(String nom);
    List<Contact> findAllByMessageContaining(String message);
}
