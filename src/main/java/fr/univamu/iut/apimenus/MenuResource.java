package fr.univamu.iut.apimenus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.Date;

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
    @Path("{id}")
    @Produces("application/json")
    public String getMenu(@PathParam("id") int id) {
        String result = service.getMenuJSON(id);

        if (result == null)
            throw new NotFoundException();

        return result;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createMenu(Menu menu) {
        Menu newMenu = service.createMenu(menu.getName(), menu.getCreatorName());
        return Response.ok(newMenu).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateMenu(@PathParam("id") int id, Menu menu) {
        if (!service.updateMenu(id, menu))
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }


    @DELETE
    @Path("{id}")
    public Response deleteMenu(@PathParam("id") int id) {
        if (!service.deleteMenu(id))
            throw new NotFoundException();
        else
            return Response.ok("deleted").build();
    }


}

