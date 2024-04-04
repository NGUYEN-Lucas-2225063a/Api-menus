package fr.univamu.iut.apimenus;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.NotFoundException;

import java.util.Date;
import java.util.List;

public class MenuService {

    protected PlatRepositoryInterface platRepo;
    protected MenuRepositoryInterface menuRepo;

    public MenuService(MenuRepositoryInterface menuRepo, PlatRepositoryInterface platRepo) {
        this.menuRepo = menuRepo;
        this.platRepo = platRepo;
    }

    public String getAllMenusJSON() {
        List<Menu> allMenus = menuRepo.getAllMenus();

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allMenus);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public String getMenuJSON(int id) {
        String result = null;
        Menu menu = menuRepo.getMenu(id);

        if (menu != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(menu);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }


    public Menu createMenu(String name, String creatorName) {
        // Génération de l'ID automatiquement en fonction des menus existants
        List<Menu> allMenus = menuRepo.getAllMenus();
        int nextId = 1;
        for (Menu menu : allMenus) {
            if (menu.getId() >= nextId) {
                nextId = menu.getId() + 1;
            }
        }

        // Obtention de la date actuelle
        Date currentDate = new Date();

        // Création du menu avec l'ID généré automatiquement et la date actuelle
        Menu menu = new Menu(nextId, name, creatorName, currentDate);
        return menuRepo.addMenu(menu);
    }

    public boolean updateMenu(int id, Menu Menu) {
        return menuRepo.updateMenu(id, Menu.getName()) ;
    }

    public boolean deleteMenu(int id) {

        return menuRepo.deleteMenu(id);
    }


    boolean registerMenu(int id) {
        boolean result = false;

        // récupération des informations du plat
        Plat myPlat = platRepo.getPlat(id);

        // si le menu n'est pas trouvé
        if (myPlat == null)
            throw new NotFoundException("menu not exists");

        return result;
    }

    boolean removeMenu(int id) {
        boolean result = false;

        // récupération des informations du plat
        Plat myPlat = platRepo.getPlat(id);

        //si le plat n'est pas trouvé
        if( myPlat == null )
            throw  new NotFoundException("Dish not exists");

        return result;
    }

}



