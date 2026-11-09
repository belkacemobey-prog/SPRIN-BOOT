package com.polytechnique.ds.gestioncatalogue5gr4.services;

import com.polytechnique.ds.gestioncatalogue5gr4.entities.Produit;
import com.polytechnique.ds.gestioncatalogue5gr4.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor//tawedh constructeur
public class ServiceProduit implements IServiceProduit{

    private ProduitRepository produitRepository;

    /*public ServiceProduit(ProduitRepository produitRepository) { awadhneha bel @allargsconstructor
        this.produitRepository = produitRepository;
    }*/

    @Override
    public void ajouterProduit(Produit p) {
        produitRepository.save(p);
    }

    @Override
    public void mettreAjourProduit(Long id, Produit p) {
        Produit pp=getProduit(id);
        if (pp != null)
        {
            pp.setNom(p.getNom());
            pp.setPrix(p.getPrix());
            pp.setQuantite(p.getQuantite());
            produitRepository.save(pp);
        }

    }

    @Override
    public void supprimerProduit(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public Produit getProduit(Long id) {
        return produitRepository.findById(id).orElse(null);//(.get ken sure , sinon .roelse)
    }

    @Override
    public List<Produit> getAllProduit() {
        return produitRepository.findAll();
    }

    @Override
    public List<Produit> getProduitParMC(String mc) {
        return produitRepository.findByNomContains(mc);
    }
}
