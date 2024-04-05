package fr.univamu.iut.apimenus;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public String getMenuJSON(int menuId) {
        String result = null;
        Menu menu = menuRepo.getMenu(menuId);

        if (menu != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(menu);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }


    public String getAllPlatsJSON() {
        List<Plat> allPlats = menuRepo.getAllPlats();

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allPlats);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String getPlatsFromMenuJSON(int id) {
        List<Plat> plats = menuRepo.getPlatsByMenu(id);

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(plats);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public String getPlatsByMenuJSON(int id) {
        List<Plat> plats = menuRepo.getPlatsByMenu(id);

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(plats);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public String getDateJSON(int menuId) {
        Menu menu = menuRepo.getMenu(menuId);
        String dateStr = menu.getCreationDate();

        // Convertir la date String en objet LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);

        // Ajouter 1 heure à la date
        LocalDateTime deliveryDateTime = dateTime.plusHours(1);

        // Convertir l'objet LocalDateTime en String
        String deliveryDateStr = deliveryDateTime.format(formatter);

        // création du json et conversion de la date de livraison
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(deliveryDateStr);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }


    public String addMenu(Menu menu) {
        try {
            menuRepo.addMenu(menu);
            return "menu added";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "menu not added";
        }
    }


    public String updateMenu(Menu menu) {
        try {
            menuRepo.updateMenu(menu);
            return "menu updated";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "menu not updated";
        }
    }

    public String updateMenu(Menu menu, String creationDate) {
        try {
            menuRepo.updateMenu(menu, creationDate);
            return "menu updated";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "menu not updated";
        }
    }

    public boolean deleteMenu(int id) {
        return menuRepo.deleteMenu(id);
    }

    public String addPlat(Plat plat) {
        try {
            menuRepo.addPlat(plat);
            return "plat added";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "plat not added";
        }
    }

    public String addPlat(int id, String nom, String description, double prix, String createurNom) {
        Plat plat = new Plat(id, nom, description, prix, createurNom);
        try {
            menuRepo.addPlat(plat);
            return "plat added";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "plat not added";
        }
    }

    public String deletePlat(int id) {
        try {
            menuRepo.deletePlat(id);
            return "plat deleted";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "plat not deleted";
        }
    }

}



