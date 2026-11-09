package com.polytechnique.ds.gestioncatalogue5gr4;

import com.polytechnique.ds.gestioncatalogue5gr4.entities.Categorie;
import com.polytechnique.ds.gestioncatalogue5gr4.entities.Produit;
import com.polytechnique.ds.gestioncatalogue5gr4.repository.CategorieRepository;
import com.polytechnique.ds.gestioncatalogue5gr4.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionCatalogue5Gr4Application {

    //@Bean
    public CommandLineRunner commandLineRunner(ProduitRepository pr, CategorieRepository cr) {
        return args -> {
            Categorie c1 = new Categorie(null, "informatique", null);
            Categorie c2 = Categorie.builder().nom("electronique").build();

            cr.save(c1);
            cr.save(c2);

            pr.save(Produit.builder()
                    .nom("pc")
                    .quantite(5)
                    .prix(5000)
                    .categorie(c1)
                    .build());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(GestionCatalogue5Gr4Application.class, args);
    }
}