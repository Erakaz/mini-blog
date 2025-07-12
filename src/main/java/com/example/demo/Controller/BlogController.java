package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @GetMapping("/")
    public String acceuil(Model model) {
        model.addAttribute("titre", "Bienvenue sur ce blog !");
        model.addAttribute("message", "Cette page a été faite grâce à Spring Boot !");
        return "index";
    }

    @GetMapping("/contact")
    public String afficher_formulaire_contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String recevoir_reponse_formulaire(@RequestParam("nom") String nom, @RequestParam("email") String email, @RequestParam("message") String message, Model model) {
        System.out.println("Nouveau message reçu : ");
        System.out.println("nom : " + nom);
        System.out.println("email : " + email);
        System.out.println("message : " + message);

        model.addAttribute("nomUtilisateur", nom);
        model.addAttribute("confirmation", "Votre message a bien été transmis !");

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
