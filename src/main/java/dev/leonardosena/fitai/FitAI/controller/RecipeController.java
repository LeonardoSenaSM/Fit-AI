package dev.leonardosena.fitai.FitAI.controller;

import dev.leonardosena.fitai.FitAI.dto.FoodDTO;
import dev.leonardosena.fitai.FitAI.service.ChatGptService;
import dev.leonardosena.fitai.FitAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    private FoodItemService foodItemService;
    private ChatGptService chatGptService;

    public RecipeController(ChatGptService chatGptService, FoodItemService foodItemService) {
        this.chatGptService = chatGptService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generatRecipe(){
        List<FoodDTO> foodItemsDTO = foodItemService.listall();
        return chatGptService.generatRecipe(foodItemsDTO)
                .map(recipe ->ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
