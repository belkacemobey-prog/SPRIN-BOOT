package com.polytechnique.ds.gestioncatalogue5gr4.controller;

import com.polytechnique.ds.gestioncatalogue5gr4.entities.Categorie;
import com.polytechnique.ds.gestioncatalogue5gr4.services.ICategorieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
@AllArgsConstructor
public class CategorieController {

    private final ICategorieService categorieService;

    // 1. Ajouter
    @PostMapping("/add")
    public void addCategorie(@RequestBody Categorie c) {
        categorieService.ajouterCategorie(c);
    }

    // 2. Liste
    @GetMapping("/all")
    public List<Categorie> getAll() {
        return categorieService.getAllCategories();
    }

    // 3. Récupérer par ID
    @GetMapping("/{id}")
    public Categorie getById(@PathVariable Long id) {
        return categorieService.getCategorie(id);
    }

    // 4. Supprimer
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categorieService.supprimerCategorie(id);
    }
}