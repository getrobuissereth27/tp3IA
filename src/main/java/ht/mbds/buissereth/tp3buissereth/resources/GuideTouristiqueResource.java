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
            // Appel à l'API du LLM via notre service LangChain4j injecté
            String reponseJson = llmClient.getGuideService().obtenirInfosLieu(villeOuPays, nbEndroits);

            // Construction de la réponse HTTP enrichie (CORS inclus)
            return Response.ok(reponseJson)
                    .header("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0")
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();

        } catch (Exception e) {
            // Gestion d'erreur propre en cas de défaillance du LLM ou de clé API absente
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erreur\": \"Impossible de joindre l'IA : " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
