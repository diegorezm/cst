package com.app.cst.controllers.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
     @GetMapping("/")
     public String index(Model model) {
          return "pages/index";
     }

     @GetMapping("/about")
     public String about(Model model) {
          return "pages/about";
     }

     @GetMapping("/services")
     public String services(Model model) {
          return "pages/services";
     }
}
