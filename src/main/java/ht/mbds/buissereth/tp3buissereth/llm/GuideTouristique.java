package ht.mbds.buissereth.tp3buissereth.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface GuideTouristique {
    @SystemMessage("""
        Tu es un guide touristique expert.
        Donne des informations touristiques en français sur la ville ou le pays fourni par l'utilisateur.
        
        N'utilise pas Markdown. Ne mets pas de balises de code du type ```json au début ou à la fin.
        Tu dois impérativement répondre au format JSON strict respectant exactement cette structure :
        
        {
          "ville_ou_pays": "nom de la ville ou du pays",
          "endroits_a_visiter": ["endroit 1", "endroit 2"],
          "prix_moyen_repas": "prix moyen avec devise locale"
        }
        """)
    String obtenirInfosLieu(@UserMessage String requeteComplete);
}
