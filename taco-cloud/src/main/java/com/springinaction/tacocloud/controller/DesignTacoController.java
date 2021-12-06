package com.springinaction.tacocloud.controller;

import com.springinaction.tacocloud.domain.Ingredient;
import com.springinaction.tacocloud.domain.Ingredient.Type;
import com.springinaction.tacocloud.domain.Taco;
import com.springinaction.tacocloud.model.IngredientRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

  private final IngredientRepository ingredientRepository;

  private final Type[] ingredientTypes = Type.values();

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = ingredientRepository.findAll();

    Arrays.stream(ingredientTypes)
        .forEach(
            type ->
                model.addAttribute(
                    type.toString().toLowerCase(Locale.ENGLISH), filterByType(ingredients, type)));
  }

  @GetMapping
  public String showDesignForm(Model model) {
    log.info("Show taco design form");
    model.addAttribute("taco", new Taco());
    return "design";
  }

  @PostMapping
  public String processTaco(@Valid @ModelAttribute("taco") Taco taco, Errors errors) {
    if (errors.hasErrors()) {
      log.error("Encountered errors while processing a taco {}", errors);
      return "design";
    }

    log.info("Processing a taco {}", taco);
    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream().filter(x -> x.getType() == type).collect(Collectors.toList());
  }
}
