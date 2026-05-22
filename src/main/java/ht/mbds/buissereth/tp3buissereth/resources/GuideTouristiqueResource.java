package ht.mbds.buissereth.tp3buissereth.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/guide")
public class GuideTouristiqueResource {
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces("text/plain")
    public String getLieuTexte(@PathParam("ville_ou_pays") String villeOuPays) {
        // Test initial : on retourne directement la String reçue
        return villeOuPays;
    }
}
