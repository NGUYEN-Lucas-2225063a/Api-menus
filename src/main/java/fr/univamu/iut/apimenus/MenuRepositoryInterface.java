package fr.univamu.iut.apimenus;

import java.util.ArrayList;
import java.util.List;

public interface MenuRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les Menus
     */
    public void close();

    /**
     * Méthode retournant le Menu dont l'ID est passé en paramètre
     * @param id identifiant du Menu recherché
     * @return un objet Menu représentant le Menu recherché
     */
    public Menu getMenu(int id);

    /**
     * Méthode retournant la liste de tous les Menus
     * @return une liste d'objets Menus
     */
    public List<Menu> getAllMenus();


    public boolean updateMenu(int id, String name);

    boolean updateMenu(int id, String name, String creatorName);

    /**
     * Méthode permettant d'ajouter un nouveau Menu
     * @param Menu objet Menu représentant le Menu à ajouter
     * @return l'objet Menu ajouté
     */
    public Menu addMenu(Menu Menu);

    /**
     * Méthode permettant de supprimer un Menu existant
     * @param id identifiant du Menu à supprimer
     * @return true si le Menu existe et a été supprimé, false sinon
     */
    public boolean deleteMenu(int id);


}
