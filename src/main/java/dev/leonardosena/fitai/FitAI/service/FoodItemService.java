package dev.leonardosena.fitai.FitAI.service;

import dev.leonardosena.fitai.FitAI.dto.FoodDTO;
import dev.leonardosena.fitai.FitAI.mapper.FoodMapper;
import dev.leonardosena.fitai.FitAI.model.FoodItem;
import dev.leonardosena.fitai.FitAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodItemService {

    private FoodItemRepository repository;
    private FoodMapper mapper;

    public FoodItemService(FoodItemRepository repository, FoodMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FoodDTO saveNew (FoodDTO foodDTO){
        FoodItem foodItem = mapper.map(foodDTO);
        foodItem = repository.save(foodItem);
        return mapper.map(foodItem);
    }

    public List<FoodDTO> listall(){
        List<FoodItem> foodItems = repository.findAll();
        return foodItems.stream().map(mapper::map).collect(Collectors.toList());
    }

    public FoodDTO listToId(Long id){
        Optional<FoodItem> foodForId = repository.findById(id);
        return foodForId.map(mapper::map).orElse(null);
    }

    public FoodDTO alterFood (Long id, FoodDTO foodDTO ){
        Optional<FoodItem> foodPresent = repository.findById(id);
        if (foodPresent.isPresent()){
            FoodItem foodChanged = mapper.map(foodDTO);
            foodChanged.setId(id);
            FoodItem foodSave = repository.save(foodChanged);
            return mapper.map(foodSave);
        }
        throw new RuntimeException("O ID " + id + " não existe!");
    }

    public void deleteFoodItem(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new RuntimeException("O ID " + id + " não existe!");
        }
    }
}