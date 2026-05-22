package ht.mbds.buissereth.tp3buissereth.llm;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LlmClientForGuide {
    private GuideTouristique guideService;

        @PostConstruct
        public void init () {
            String apiKey = System.getenv("GEMINI_API_KEY");

            var model = GoogleAiGeminiChatModel.builder()
                    .apiKey(apiKey)
                    .modelName("gemini-2.5-flash")
                    .build();

            this.guideService = AiServices.builder(GuideTouristique.class)
                    .chatModel(model)
                    .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                    .build();
        }

        public GuideTouristique getGuideService () {
            return this.guideService;
        }
    }
