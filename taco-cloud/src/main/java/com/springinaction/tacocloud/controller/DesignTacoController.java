package com.springinaction.tacocloud.controller;

import com.springinaction.tacocloud.domain.Ingredient;
import com.springinaction.tacocloud.domain.Ingredient.Type;
import com.springinaction.tacocloud.domain.Taco;
import com.springinaction.tacocloud.domain.TacoOrder;
import com.springinaction.tacocloud.domain.User;
import com.springinaction.tacocloud.model.IngredientRepository;
import com.springinaction.tacocloud.model.TacoRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.springinaction.tacocloud.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  private TacoRepository tacoRepo;

  private UserRepository userRepo;

  @Autowired
  public DesignTacoController(
      IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
    this.ingredientRepo = ingredientRepo;
    this.tacoRepo = tacoRepo;
    this.userRepo = userRepo;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(ingredients::add);

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(
          type.toString().toLowerCase(Locale.ENGLISH), filterByType(ingredients, type));
    }
  }

  @ModelAttribute(name = "order")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @ModelAttribute(name = "user")
  public User user(Principal principal) {
    String username = principal.getName();
    User user = userRepo.findByUsername(username);
    return user;
  }

  @GetMapping
  public String showDesignForm(Model model) {
    return "design";
  }

  @PostMapping
  public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder order) {

    if (errors.hasErrors()) {
      return "design";
    }

    Taco saved = tacoRepo.save(taco);
    order.addTaco(saved);

    return "redirect:/orders/current";
  }

  private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream().filter(x -> type == x.getType()).collect(Collectors.toList());
  }
}
