package com.polytechnique.ds.gestioncatalogue5gr4.services;

import com.polytechnique.ds.gestioncatalogue5gr4.entities.Produit;

import java.util.List;

public interface IServiceProduit {

    void ajouterProduit(Produit p);
    void mettreAjourProduit(Long id,Produit p);
    void supprimerProduit(Long id);
    Produit getProduit(Long id);
    List<Produit> getAllProduit();
    List<Produit>getProduitParMC(String mc);
}
