package ht.mbds.buissereth.tp3buissereth.llm;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristique {
    @SystemMessage("""
        Tu es un guide touristique expert.
        Donne des informations touristiques en français sur la ville ou le pays fourni.
        
        N'utilise pas Markdown. Ne mets pas de balises de code du type ```json au début ou à la fin. 
        Donne uniquement une chaîne de caractères au format JSON strict respectant exactement cette structure :
        
        {
          "ville_ou_pays": "nom de la ville ou du pays",
          "endroits_a_visiter": ["endroit 1", "endroit 2"],
          "prix_moyen_repas": "<prix> <devise du pays>"
        }
        """)
    @UserMessage("Donne-moi des informations sur : {lieu}. Je veux voir exactement les {nb_endroits} principaux endroits à visiter.")
    String obtenirInfosLieu(@V("lieu") String lieu, @V("nb_endroits") int nbEndroits);
}
