package fr.univamu.iut.apimenus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import static java.awt.SystemColor.menu;

@Path("/menus")

public class MenuResource extends MenuApplication {

    private MenuService service;

    public MenuResource() {}

    /**
     * Constructeur prenant un service en paramètre.
     * @param service Le service à utiliser pour manipuler les menus.
     */
    public MenuResource(MenuService service) {
        this.service = service;
    }

    /**
     * Constructeur permettant d'initialiser le service avec des objets implémentant les interfaces d'accès aux données.
     * @param MenuRepo Objet implémentant l'interface MenuRepositoryInterface.
     * @param PlatRepo Objet implémentant l'interface PlatRepositoryInterface.
     */
    public @Inject MenuResource(MenuRepositoryInterface MenuRepo, PlatRepositoryInterface PlatRepo){
        this.service = new MenuService(MenuRepo, PlatRepo) ;
    }

    @GET
    @Produces("application/json")
    public String getAllMenus() {
        return service.getAllMenusJSON();
    }

    @GET
    @Path("{menuId}")
    @Produces("application/json")
    public String getMenu(@PathParam("menuId") int id) {
        String result = service.getMenuJSON(id);

        if (result == null)
            throw new NotFoundException();

        return result;
    }

    @GET
    @Path("plats")
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }

    @GET
    @Path("plats/{id}")
    @Produces("application/json")
    public String getPlatsFromMenu(@PathParam("id") int id) {
        String result = service.getPlatsFromMenuJSON(id);

        if (result == null)
            throw new NotFoundException();

        return result;
    }

    @GET
    @Path("plats/menu={id}")
    @Produces("application/json")
    public String getPlatsByMenu(@PathParam("id") int id) {
        String result = service.getPlatsByMenuJSON(id);

        if (result == null)
            throw new NotFoundException();

        return result;
    }

    @GET
    @Path("{id}/date")
    @Produces("application/json")
    public String getDateJSON(@PathParam("id") int id) {
        String result = service.getDateJSON(id);

        if (result == null)
            throw new NotFoundException();

        return result;
    }

    // curl -X POST -H "Content-Type: application/json" -d '{"name": "Menu 1", "creatorName": "Lucas"}' http://localhost:8080/apimenus-1.0-SNAPSHOT/api/menus
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addMenu(Menu menu) {
        return service.addMenu(menu);
    }


    // curl -X POST -H "Content-Type: application/json" -d '[{"id":"30","nom":"Phôv2","description":"Soupe viet","prix":"100","createurNom":"Lucas"},{"id":"31","nom":"Phôv2","description":"Soupe viet","prix":"100","createurNom":"Lucas"}]' http://localhost:8080/apimenus-1.0-SNAPSHOT/api/menus/plats/14

    @POST
    @Path("plats/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public String addPlat(Plat plat, @PathParam("id") int id) {
        plat.setId(id);
        return service.addPlat(plat);
    }

    // curl -X PUT -H "Content-Type: application/json" -d '{"name": "Menu 5", "menuId": "1"}' http://localhost:8080/apimenus-1.0-SNAPSHOT/api/menus/1
    // curl -X PUT -H "Content-Type: application/json" -d '{"name": "Menu 5"}' http://localhost:8080/apimenus-1.0-SNAPSHOT/api/menus/1
    @PUT
    @Path("{menuId}")
    @Consumes("application/json")
    public String updateMenu(@PathParam("menuId") int id, Menu menu) {
        menu.setMenuId(id);
        String result = service.updateMenu(menu);

        // si le menu n'a pas été trouvé
        if (result == null)
            throw new NotFoundException();
        return result;
    }

    // curl -X PUT -H "Content-Type: application/json" -d '{"menuId":"1","name": "Menu 1"}' http://localhost:8080/apimenus-1.0-SNAPSHOT/api/menus/1/date="2021-12-31"
    @PUT
    @Path("{id}/date={creationDate}")
    @Consumes("application/json")
    @Produces("application/json")
    public String updateMenu(Menu menu, @PathParam("id") int id, @PathParam("creationDate") String creationDate) {
        menu.setMenuId(id);
        String result = service.updateMenu(menu, creationDate);

        // si le menu n'a pas été trouvé
        if (result == null)
            throw new NotFoundException();
        return result;
    }

    // curl -X DELETE http://localhost:8080/apimenus-1.0-SNAPSHOT/api/menus/{menuId}
    @DELETE
    @Path("{menuId}")
    public Response deleteMenu(@PathParam("menuId") int id) {
        if (!service.deleteMenu(id))
            throw new NotFoundException();
        else
            return Response.ok("deleted").build();
    }


    // curl -X DELETE http://localhost:8080/apimenus-1.0-SNAPSHOT/api/menus/plats/{id}
    @DELETE
    @Path("plats/menu={id}")
    public String deletePlat(@PathParam("id") int id) {
        return service.deletePlat(id);
    }

}


