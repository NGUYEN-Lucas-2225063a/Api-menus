package fr.univamu.iut.apimenus;

import java.util.ArrayList;

/**
 * Interface décrivant les opérations disponibles pour manipuler les menus dans le dépôt de données.
 */
public interface MenuRepositoryInterface {

    /**
     * Méthode fermant le dépôt où sont stockées les informations sur les Menus.
     */
    public void close();

    /**
     * Récupère un menu à partir de son identifiant.
     * @param menuId L'identifiant du menu à récupérer.
     * @return Le menu correspondant à l'identifiant spécifié.
     */
    public Menu getMenu(int menuId);

    /**
     * Récupère tous les menus présents dans le dépôt.
     * @return Une liste contenant tous les menus.
     */
    public ArrayList<Menu> getAllMenus();

    /**
     * Ajoute un nouveau menu au dépôt.
     * @param menu Le menu à ajouter.
     */
    void addMenu(Menu menu);

    /**
     * Ajoute un plat au dépôt.
     * @param plat Le plat à ajouter.
     */
    void addPlat(Plat plat);

    /**
     * Supprime un menu du dépôt.
     * @param id L'identifiant du menu à supprimer.
     * @return True si la suppression a réussi, False sinon.
     */
    boolean deleteMenu(int id);

    /**
     * Récupère un plat à partir de son identifiant.
     * @param platId L'identifiant du plat à récupérer.
     * @return Le plat correspondant à l'identifiant spécifié.
     */
    Plat getPlat(int platId);

    /**
     * Récupère tous les plats associés à un menu spécifique.
     * @param menuId L'identifiant du menu.
     * @return Une liste contenant tous les plats du menu spécifié.
     */
    ArrayList<Plat> getPlatsByMenu(int menuId);

    /**
     * Récupère tous les plats présents dans le dépôt.
     * @return Une liste contenant tous les plats.
     */
    ArrayList<Plat> getAllPlats();

    /**
     * Met à jour les informations d'un menu dans le dépôt.
     * @param menu Le menu à mettre à jour.
     */
    void updateMenu(Menu menu);

    /**
     * Met à jour les informations d'un menu dans le dépôt et modifie sa date de création.
     * @param menu Le menu à mettre à jour.
     * @param creationDate La nouvelle date de création du menu.
     */
    void updateMenu(Menu menu, String creationDate);

    /**
     * Met à jour les informations d'un plat dans le dépôt.
     * @param plat Le plat à mettre à jour.
     */
    void updatePlat(Plat plat);

    /**
     * Supprime un plat du dépôt.
     * @param id L'identifiant du plat à supprimer.
     */
    void deletePlat(int id);
}
