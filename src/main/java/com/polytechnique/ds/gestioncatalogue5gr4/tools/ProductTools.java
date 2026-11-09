package com.polytechnique.ds.gestioncatalogue5gr4.tools;

import com.polytechnique.ds.gestioncatalogue5gr4.entities.Categorie;
import com.polytechnique.ds.gestioncatalogue5gr4.entities.Produit;
import com.polytechnique.ds.gestioncatalogue5gr4.repository.CategorieRepository;
import com.polytechnique.ds.gestioncatalogue5gr4.repository.ProduitRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductTools {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public ProductTools(ProduitRepository produitRepository,
                        CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    @Tool(name = "getProduct", description = "Récupère un produit par son id")
    public Produit getProduit(@ToolParam(description = "L'identifiant du produit") Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Tool(name = "allProduct", description = "Récupère la liste de tous les produits de la base de données")
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Tool(name = "getStatistiques", description = "fournir les statistiques du catalogue tels que le nombre de produits, le prix moyen, le produit le plus cher et le produit le moins cher")
    public Map<String, Object> getStatistiques() {
        Map<String, Object> map = new HashMap<>();

        List<Produit> liste = produitRepository.findAll();
        map.put("nombre de produits", liste.size());
        map.put("prix moyen", liste.stream().mapToDouble(p -> p.getPrix()).average().orElse(0));
        map.put("produit plus cher", liste.stream().max((p1, p2) -> Double.compare(p1.getPrix(), p2.getPrix())).orElse(null));
        map.put("produit moins cher", liste.stream().min((p1, p2) -> Double.compare(p1.getPrix(), p2.getPrix())).orElse(null));

        return map;
    }

    @Tool(name = "addProduct", description = "ajouter un nouveau produit à la base de données")
    public String addProduct(
            @ToolParam(description = "nom du produit", required = true) String nom,
            @ToolParam(description = "prix du produit") double prix,
            @ToolParam(description = "quantite du produit") int quantite,
            @ToolParam(description = "nom de la categorie") String nomCategorie
    ) {
        if (nom == null || nom.isBlank()) return "nom invalide";
        if (prix < 0) return "prix invalide";
        if (quantite < 0) return "quantite invalide";

        Categorie c = categorieRepository.findByNom(nomCategorie);
        if (c == null) return "categorie non trouvée";

        Produit p = new Produit();
        p.setNom(nom);
        p.setPrix(prix);
        p.setQuantite(quantite);
        p.setCategorie(c);
        produitRepository.save(p);

        return "produit ajouté avec succès";
    }

    // ===== Défi 1 : statistiques déterministes =====
    @Tool(name = "getCatalogueStatistics",
            description = "Fournit des statistiques clés sur le catalogue : total, prix moyen, produit le plus cher et le moins cher")
    public Map<String, Object> getCatalogueStatistics() {
        List<Produit> produits = produitRepository.findAll();

        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalArticles", produits.size());

        if (produits.isEmpty()) {
            stats.put("message", "Le catalogue est vide");
            return stats;
        }

        double prixMoyen = produits.stream()
                .mapToDouble(Produit::getPrix)
                .average()
                .orElse(0.0);

        Produit plusCher = produits.stream()
                .max(Comparator.comparingDouble(Produit::getPrix))
                .orElseThrow();

        Produit moinsCher = produits.stream()
                .min(Comparator.comparingDouble(Produit::getPrix))
                .orElseThrow();

        stats.put("prixMoyen", Math.round(prixMoyen * 100.0) / 100.0);
        stats.put("produitLePlusCher", Map.of("nom", plusCher.getNom(), "prix", plusCher.getPrix()));
        stats.put("produitLeMoinsCher", Map.of("nom", moinsCher.getNom(), "prix", moinsCher.getPrix()));

        return stats;
    }

    // ===== Défi 2 : création avec validation =====
    @Tool(name = "createProduct",
            description = "Crée et enregistre un nouveau produit dans la base de données MySQL")
    public Produit createProduct(
            @ToolParam(description = "Nom commercial du produit", required = true) String nom,
            @ToolParam(description = "Prix de vente en dinars (positif)", required = true) double prix,
            @ToolParam(description = "Quantité initiale en stock", required = true) int quantite,
            @ToolParam(description = "Description détaillée du produit", required = true) String description) {

        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom du produit est obligatoire.");
        }
        if (prix <= 0) {
            throw new IllegalArgumentException("Le prix doit être strictement positif (reçu : " + prix + ").");
        }
        if (quantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative (reçue : " + quantite + ").");
        }

        Produit produit = Produit.builder()
                .nom(nom.trim())
                .prix(prix)
                .quantite(quantite)
                .description(description)
                .build();

        return produitRepository.save(produit);
    }
}