package com.polytechnique.ds.gestioncatalogue5gr4.services;

import com.polytechnique.ds.gestioncatalogue5gr4.entities.Categorie;
import java.util.List;

public interface ICategorieService {
    void ajouterCategorie(Categorie c);
    List<Categorie> getAllCategories();
    Categorie getCategorie(Long id);
    void supprimerCategorie(Long id);
}