package dev.leonardosena.fitai.FitAI.service;

import dev.leonardosena.fitai.FitAI.dto.FoodDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final WebClient webClient;
    @Value("${API_KEY}")
    private String apiKey = "API_KEY";


    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generatRecipe(List<FoodDTO> foodDTOList) {

        String alimentos = foodDTOList.stream()
                .map(items -> String.format("%s (%s) - Calorias: %d, Quantidade: %s, Validade: %s",
                        items.getName(),items.getCategory(),items.getCalories(), items.getQuantity(), items.getValidity()))
                .collect(Collectors.joining("\n"));

        String prompt = "Baseado no meu banco de dados faça uma receita simples com baixo custo calorico " +
                "e tambem ultilizando os elementos que estão perto do vencimento para não estragarem, segue com os seguintes ingredientes: " + alimentos;

        Map<String, Object> resquestBody= Map.of(
                "model", "gpt-4o",
                "messages", List.of(Map.of("role", "system","content","Você e um assistente que cria receitas Fits, focando em baixo consumo calorico."),
                        Map.of("role","user", "content", prompt)
                )
        );
        return webClient.post()
                .uri("/chat/completions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(resquestBody)
                .retrieve()

                .bodyToMono(Map.class)
                .map(response ->{
                    var choices = (List<Map<String,Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()){
                        Map<String, Object> message = (Map<String,Object>) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhuma receita pode ser gerada.";
                });
    }
}