package ht.mbds.buissereth.tp3buissereth.resources;

import ht.mbds.buissereth.tp3buissereth.llm.LlmClientForGuide;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/guide")
public class GuideTouristiqueResource {
    @Inject
    private LlmClientForGuide llmClient;

    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGuideTourist(
            @PathParam("ville_ou_pays") String villeOuPays,
            @QueryParam("nb") @DefaultValue("2") int nbEndroits) {

        try {
            //ici, on construit la chaine avec les paramètres de l'utilisateur
            String requeteLlm = "Donne-moi des informations sur : " + villeOuPays
                    + ". Je veux voir exactement les " + nbEndroits
                    + " principaux endroits à visiter.";

            // On envoie la phrase toute faite au service
            String reponseJson = llmClient.getGuideService().obtenirInfosLieu(requeteLlm);

            // Envoi de la réponse avec désactivation du cache
            return Response.ok(reponseJson)
                    .header("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0")
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erreur\": \"Impossible de joindre l'IA : " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
