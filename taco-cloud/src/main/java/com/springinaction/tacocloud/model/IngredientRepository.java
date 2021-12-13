package com.springinaction.tacocloud.model;

import com.springinaction.tacocloud.domain.Ingredient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, String>, Specification<Ingredient> {}
