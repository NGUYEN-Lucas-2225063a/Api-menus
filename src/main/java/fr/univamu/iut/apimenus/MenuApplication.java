package fr.univamu.iut.apimenus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe principale de l'application des menus
 */
@ApplicationPath("/api")
@ApplicationScoped

public class MenuApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter l'API Menu au moment de la création de la ressource
     * @return une instance de l'API avec l'url à utiliser
     */
    @Produces
    private PlatRepositoryInterface connectPlatApi(){
        return new PlatRepositoryAPI("http://localhost:8080/plats-1.0-SNAPSHOT/api/");
    }

    @Produces
    private MenuRepositoryInterface openDbConnection(){
        MenuRepositoryMariadb db = null;

        try{
            db = new MenuRepositoryMariadb("jdbc:mariadb://mysql-javalog.alwaysdata.net/javalog_library_db", "javalog", "Zetsurider2*");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param MenuRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes MenuRepositoryInterface MenuRepo ) {
        MenuRepo.close();
    }

    
}
