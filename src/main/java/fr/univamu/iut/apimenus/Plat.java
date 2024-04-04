package fr.univamu.iut.apimenus;

import java.util.Date;

/// By Lucas N'guyen
/**
 * Classe représentant un plat
 */
public class Plat {

    protected int id;
    protected String nom;
    protected String description;
    protected double prix;
    protected String createurNom; // Nom de la personne ayant créé le plat
    protected Date dateCreation; // Date de création du plat

    // Constructeur
    public Plat(int id, String nom, String description, double prix, String createurNom, Date dateCreation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.createurNom = createurNom;
        this.dateCreation = dateCreation;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCreateurNom() {
        return createurNom;
    }

    public void setCreateurNom(String createurNom) {
        this.createurNom = createurNom;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", createurNom='" + createurNom + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
