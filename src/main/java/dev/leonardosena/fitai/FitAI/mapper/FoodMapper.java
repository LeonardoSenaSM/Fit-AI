package dev.leonardosena.fitai.FitAI.mapper;


import dev.leonardosena.fitai.FitAI.dto.FoodDTO;
import dev.leonardosena.fitai.FitAI.model.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper {

    public FoodItem map (FoodDTO foodDTO){
        FoodItem foodItem = new FoodItem();
        foodItem.setId(foodDTO.getId());
        foodItem.setName(foodDTO.getName());
        foodItem.setCategory(foodDTO.getCategory());
        foodItem.setQuantity(foodDTO.getQuantity());
        foodItem.setValidity(foodDTO.getValidity());
        foodItem.setCalories(foodDTO.getCalories());

        return foodItem;
    }
    public FoodDTO map(FoodItem foodItem){
    FoodDTO foodDTO = new FoodDTO();

        foodDTO.setId(foodItem.getId());
        foodDTO.setName(foodItem.getName());
        foodDTO.setCategory(foodItem.getCategory()); // Cópia direta do Enum
        foodDTO.setQuantity(foodItem.getQuantity());
        foodDTO.setValidity(foodItem.getValidity());
        foodDTO.setCalories(foodItem.getCalories());

        return foodDTO;
        }
}
