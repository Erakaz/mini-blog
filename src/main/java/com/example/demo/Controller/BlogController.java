package com.example.demo.Controller;

import com.example.demo.Model.Contact;
import com.example.demo.Service.BlogService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @PersistenceContext
    private EntityManager entityManager;
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String acceuil(Model model) {
        //blogService.creerDonneesExemple();
        model.addAttribute("titre", "Bienvenue sur ce blog !");
        model.addAttribute("message", "Cette page a été faite grâce à Spring Boot !");
        return "index";
    }

    @GetMapping("/posts")
    public String afficherTousLesPosts(Model model) {
        model.addAttribute("posts", blogService.obtenirTousLesPosts());
        model.addAttribute("titre", "Tous les articles");
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String afficherPost(@RequestParam("id") Long id, Model model) {
        Post post = blogService.obtenirPostParId(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "post-detail";
        }
        return "redirect:/posts";
    }

    @GetMapping("/contact")
    public String afficher_formulaire_contact() {
        return "contact";
    }

    @PostMapping("/contact")
    @Transactional
    public String recevoirReponseFormulaire(@RequestParam("nom") String nom, @RequestParam("email") String email, @RequestParam("message") String message, Model model) {
        System.out.println("Nouveau message reçu : ");
        System.out.println("nom : " + nom);
        System.out.println("email : " + email);
        System.out.println("message : " + message);
        model.addAttribute("nomUtilisateur", nom);
        model.addAttribute("confirmation", "Votre message a bien été transmis !");
        Contact contact = new Contact(nom, email, message);
        entityManager.persist(contact);
        entityManager.flush();
        return "confirmation";
    }

    @GetMapping("/saluer")
    public String saluer(@RequestParam("nom") String nom, Model model) {
        model.addAttribute("nomPersonne", nom);
        model.addAttribute("salutation", "Bonjour " + nom + " !");
        
        return "salutation";
    }

    @GetMapping("/bonjour")
    public String bonjourOptionnel(@RequestParam(value = "nom", defaultValue = "Visiteur") String nom, Model model) {
        model.addAttribute("nomPersonne", nom);

        return "salutation";
    }
}
