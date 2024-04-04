package fr.univamu.iut.apimenus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.Date;

import static java.awt.SystemColor.menu;
import static java.awt.SystemColor.menuText;

@Path("/menus")

public class MenuResource {

    private MenuService service;

    public MenuResource() {}

    public MenuResource(MenuService service) {
        this.service = service;
    }

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param MenuRepo objet implémentant l'interface d'accès aux données
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

    //curl -X POST -d "name=TEST&creatorName=Lucas" http://localhost:8080/apimenus-1.0-SNAPSHOT/menus
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addMenu(Menu menu) {
        return service.addMenu(menu);
    }
    @POST
    @Path("plats/menu={id}")
    @Consumes("application/json")
    @Produces("application/json")
    public String addPlat(Plat plat, @PathParam("id") int id) {
        plat.setId(id);
        return service.addPlat(plat);
    }

    @DELETE
    @Path("{menuId}")
    public Response deleteMenu(@PathParam("menuId") int id) {
        if (!service.deleteMenu(id))
            throw new NotFoundException();
        else
            return Response.ok("deleted").build();
    }


}

