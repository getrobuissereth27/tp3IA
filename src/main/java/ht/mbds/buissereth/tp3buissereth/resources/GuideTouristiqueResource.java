package ht.mbds.buissereth.tp3buissereth.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/guide")
public class GuideTouristiqueResource {
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces("application/json") // JAX-RS va transformer le tableau en JSON
    public String[] getLieuJson(@PathParam("ville_ou_pays") String villeOuPays) {
        // On retourne un tableau dont le seul élément est le paramètre de l'URL
        return new String[] { villeOuPays };
    }
}
