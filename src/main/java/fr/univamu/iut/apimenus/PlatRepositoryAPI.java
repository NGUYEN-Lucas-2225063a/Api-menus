package fr.univamu.iut.apimenus;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;
import java.util.List;

public class PlatRepositoryAPI implements PlatRepositoryInterface {

    /**
     * URL de l'API des plats
     */
    String url;

    /**
     * Constructeur initialisant l'url de l'API
     * @param url chaîne de caractères avec l'url de l'API
     */
    public PlatRepositoryAPI(String url){
        this.url = url ;
    }

    @Override
    public void close() {}


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

    @Override
    public List<Plat> getAllPlats() {
        return null;
    }

    @Override
    public boolean updatePlat(int id, String nom, String description, double prix, String createurNom) {
        return false;
    }

    @Override
    public Plat addPlat(Plat plat) {
        return null;
    }

    @Override
    public boolean deletePlat(int id) {
        return false;
    }

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
