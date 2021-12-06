package com.springinaction.tacocloud.controller;

import com.springinaction.tacocloud.domain.Taco;
import com.springinaction.tacocloud.domain.TacoOrder;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

  @GetMapping("/current")
  public String orderForm(Model model) {
    var tacoOrder = new TacoOrder();
    var taco = (Taco) model.getAttribute("taco");
    tacoOrder.addTaco(taco);
    model.addAttribute("tacoOrder", tacoOrder);
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid TacoOrder order, Errors errors) {
    if (errors.hasErrors()) {
      log.error("Error processing order {}", errors);
      return "orderForm";
    }
    log.info("Order submitted: " + order);
    return "redirect:/";
  }
}
