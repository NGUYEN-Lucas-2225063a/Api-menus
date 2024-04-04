package fr.univamu.iut.apimenus;

import java.util.ArrayList;
import java.util.List;

public interface MenuRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les Menus
     */
    public void close();

    public Menu getMenu(int menuId);

    public ArrayList<Menu> getAllMenus();

    public void addMenu(Menu Menu);

    public void addPlat(Plat plat);

    public boolean deleteMenu(int id);

    Plat getPlat(int platId);

    ArrayList<Plat> getPlatsByMenu(int menuId);
    
    ArrayList<Plat> getAllPlats();

    void updateMenu(Menu menu);

    void updateMenu(Menu menu, String creationDate);
    void updatePlat(Plat plat);

    void deletePlat(int id);
}
