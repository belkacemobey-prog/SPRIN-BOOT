package com.polytechnique.ds.gestioncatalogue5gr4.entities; // ⚠️ CHANGE CECI si ton dossier a un autre nom !

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Categorie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;



    @OneToMany(mappedBy = "categorie", fetch = FetchType.LAZY)
    //cascade
    //eager=/lazy
    @JsonIgnore
    private List<Produit> produits;
}