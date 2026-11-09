package com.polytechnique.ds.gestioncatalogue5gr4.repository;

import com.polytechnique.ds.gestioncatalogue5gr4.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long>  {

    Categorie findByNom(String nom);
}