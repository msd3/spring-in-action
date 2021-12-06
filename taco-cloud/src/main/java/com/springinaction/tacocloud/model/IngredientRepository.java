package com.springinaction.tacocloud.model;

import com.springinaction.tacocloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {}
