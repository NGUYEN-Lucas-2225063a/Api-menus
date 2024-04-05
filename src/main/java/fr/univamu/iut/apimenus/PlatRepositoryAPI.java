package fr.univamu.iut.apimenus;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Implémentation de l'interface PlatRepositoryInterface utilisant une API RESTful pour accéder aux données des plats.
 */
public class PlatRepositoryAPI implements PlatRepositoryInterface {

    /**
     * URL de l'API des plats
     */
    String url;

    /**
     * Constructeur initialisant l'URL de l'API.
     *
     * @param url chaîne de caractères avec l'URL de l'API
     */
    public PlatRepositoryAPI(String url){
        this.url = url ;
    }

    @Override
    public void close() {}

    /**
     * Récupère un plat à partir de son identifiant.
     *
     * @param id identifiant du plat à récupérer
     * @return le plat correspondant à l'identifiant, null si non trouvé
     */
    @Override
    public Plat getPlat( int id ) {
        Plat myPlat = null;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget platResource  = client.target(url);
        // définition du point d'accès
        WebTarget platEndpoint = platResource.path("plats/"+id);
        // envoi de la requête et récupération de la réponse
        Response response = platEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si le plat a bien été trouvé, conversion du JSON en Plat
        if( response.getStatus() == 200)
            myPlat = response.readEntity(Plat.class);

        // fermeture de la connexion
        client.close();
        return myPlat;
    }

    /**
     * Récupère tous les plats.
     *
     * @return une liste contenant tous les plats
     */
    @Override
    public List<Plat> getAllPlats() {
        return null;
    }

    /**
     * Met à jour un plat.
     *
     * @param id identifiant du plat à mettre à jour
     * @param nom le nouveau nom du plat
     * @param description la nouvelle description du plat
     * @param prix le nouveau prix du plat
     * @param createurNom le nouveau nom du créateur du plat
     * @return true si la mise à jour a réussi, false sinon
     */
    @Override
    public boolean updatePlat(int id, String nom, String description, double prix, String createurNom) {
        return false;
    }

    /**
     * Ajoute un plat.
     *
     * @param plat le plat à ajouter
     * @return le plat ajouté
     */
    @Override
    public Plat addPlat(Plat plat) {
        return null;
    }

    /**
     * Supprime un plat.
     *
     * @param id identifiant du plat à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    @Override
    public boolean deletePlat(int id) {
        return false;
    }

    /**
     * Met à jour un plat avec la date de création.
     *
     * @param id identifiant du plat à mettre à jour
     * @param nom le nouveau nom du plat
     * @param description la nouvelle description du plat
     * @param prix le nouveau prix du plat
     * @param createurNom le nouveau nom du créateur du plat
     * @param dateCreation la nouvelle date de création du plat
     * @return true si la mise à jour a réussi, false sinon
     */
    @Override
    public boolean updatePlat(int id, String nom, String description, double prix, String createurNom, Date dateCreation) {
        boolean result = false ;

        Plat updatedPlat = new Plat(id, nom, description, prix, createurNom);

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget platResource  = client.target(url);
        // définition du point d'accès
        WebTarget platEndpoint = platResource.path("plats/"+id);
        // envoi de la requête avec le plat en JSON et récupération de la réponse
        Response response = platEndpoint.request(MediaType.APPLICATION_JSON)
                .put( Entity.entity(updatedPlat, MediaType.APPLICATION_JSON) );

        // si la mise à jour a été faite
        if( response.getStatus() == 200)
            result = true;

        // fermeture de la connexion
        client.close();

        return result;
    }
}
