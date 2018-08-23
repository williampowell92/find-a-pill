package com.makersacademy.find_a_pill.controller;

import com.makersacademy.find_a_pill.model.Pill;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
  private static List<Pill> pills = new ArrayList<Pill>();

  static {
    pills.add(new Pill("first", "http://www.first.com", "First summary"));
    pills.add(new Pill("second", "http://www.second.com", "Second summary"));
  }

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("pills", pills);

    return "index";
  }
}
