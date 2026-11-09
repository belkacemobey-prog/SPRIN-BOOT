package com.polytechnique.ds.gestioncatalogue5gr4.controller;

import com.polytechnique.ds.gestioncatalogue5gr4.entities.Produit;
import com.polytechnique.ds.gestioncatalogue5gr4.services.IServiceProduit;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
//@CrossOrigin("*")
@AllArgsConstructor
public class ProduitController {
    private IServiceProduit serviceProduit;

    @GetMapping("/all")

    public List<Produit> getAllProducts()

    {
        return serviceProduit.getAllProduit();
    }

    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable Long id){
        return serviceProduit.getProduit(id);
    }

    @GetMapping("/search")
    public List<Produit> getProductsBkw(@RequestParam String mc){
        return serviceProduit.getProduitParMC(mc);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        serviceProduit.supprimerProduit(id);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Produit p)
    {
        serviceProduit.ajouterProduit(p);
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Produit p){
        serviceProduit.mettreAjourProduit(id ,p);
    }//testi b spring boot swaagger (nhez dependance maven fi pom.xml tokhj m tekliqui aleha w tlaci projet w ambaed thel nav localhost:8081\swagger-ui\index.html(deja fel cours) nkamlou ajout-liste-recup cat par id supp categ  coatgrorie service et controller
}
