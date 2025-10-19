package dev.leonardosena.fitai.FitAI.controller;

import dev.leonardosena.fitai.FitAI.dto.FoodDTO;
import dev.leonardosena.fitai.FitAI.mapper.FoodMapper;
import dev.leonardosena.fitai.FitAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService service;
    private FoodMapper mapper;

    public FoodItemController(FoodItemService service, FoodMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    //GET
    @GetMapping("/listall")
    public ResponseEntity<List<FoodDTO>> listall(){
        List<FoodDTO> foodDTOList= service.listall();
        return ResponseEntity.ok(foodDTOList);
    }

    //GET
    @GetMapping("listall/{id}")
    public ResponseEntity<?> listToId(@PathVariable Long id){
        FoodDTO foodDTOtoid = service.listToId(id);
        if (foodDTOtoid != null){
            return ResponseEntity.ok(foodDTOtoid);
        }else {
            String error = "O Id: " +id+ " não foi encontrado!";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }
    //POST
    @PostMapping("/create")
    public ResponseEntity<FoodDTO> create(@RequestBody FoodDTO foodDTO){
        FoodDTO save = service.saveNew(foodDTO);
        return ResponseEntity.ok(save);
    }
    //UPDATE
    @PutMapping("/change/{id}")
    public ResponseEntity<?> change(@PathVariable Long id,@RequestBody FoodDTO foodAlter){
      try {
          FoodDTO foodChanged = service.alterFood(id, foodAlter);
          if(foodChanged == null){
              return new ResponseEntity<>("ID not found: " + id ,HttpStatus.NOT_FOUND);
          }
          return ResponseEntity.ok("Atualizado " + foodChanged);
      } catch (NumberFormatException e){
          return new ResponseEntity<>("ID inválido: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
          System.err.println("Erro interno: " + e.getMessage());
          return new ResponseEntity<>("Ocorreu um erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            service.deleteFoodItem(id);
            return ResponseEntity.ok("Id deletado com sucesso: \nId - '" + id +"'");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}