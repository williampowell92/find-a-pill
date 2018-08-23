package com.makersacademy.find_a_pill.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @RequestMapping("/")
  public String welcome() {
    return "welcome";
  }
}
