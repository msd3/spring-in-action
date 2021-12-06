package com.springinaction.tacocloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Deprecated
@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "home";
  }
}
