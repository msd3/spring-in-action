package com.springinaction.tacocloud.rest;

import com.springinaction.tacocloud.database.Ingredient;
import com.springinaction.tacocloud.database.IngredientsDao;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    final IngredientsDao ingredientsDao;

    public IngredientController(IngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }

    @GetMapping
    public Stream<Ingredient> ingredients() {
        return ingredientsDao.findAll();
    }
    
    @PostMapping(consumes = TEXT_PLAIN_VALUE)
    public UUID createIngredient(@RequestBody String ingredientName){
        return ingredientsDao.create(ingredientName);
    }


}
