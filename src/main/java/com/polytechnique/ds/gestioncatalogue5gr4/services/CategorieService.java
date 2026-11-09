package com.polytechnique.ds.gestioncatalogue5gr4.services;

import com.polytechnique.ds.gestioncatalogue5gr4.entities.Categorie;
import com.polytechnique.ds.gestioncatalogue5gr4.entities.Produit;
import com.polytechnique.ds.gestioncatalogue5gr4.repository.CategorieRepository;
import com.polytechnique.ds.gestioncatalogue5gr4.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategorieService implements ICategorieService {

    private final CategorieRepository categorieRepository;

    @Override
    public void ajouterCategorie(Categorie c) {
        categorieRepository.save(c);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getCategorie(Long id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
    }

    @Override
    public void supprimerCategorie(Long id) {
        Categorie c = categorieRepository.findById(id).orElse(null);

        if (c != null) {
            for (Produit p : c.getProduits()) {
                p.setCategorie(null);
            }

            categorieRepository.deleteById(id);
        }
    }
}