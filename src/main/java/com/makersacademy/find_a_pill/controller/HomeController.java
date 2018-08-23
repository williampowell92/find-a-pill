package com.makersacademy.find_a_pill.controller;

import com.makersacademy.find_a_pill.model.Pill;
import com.makersacademy.find_a_pill.service.PillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
  @Autowired
  private PillService pillService;

  private static Page<Pill> pills;

  @RequestMapping("/")
  public String index(Model model, @RequestParam(value="query", required=false) String query) {
    if (query != null) {
      pills = pillService.findByTitleAndTagAndSummaryWithCustomQuery(query, PageRequest.of(0, 10));
      model.addAttribute("pills", pills.getContent());
    }

    return "index";
  }
}
