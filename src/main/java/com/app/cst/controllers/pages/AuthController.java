package com.app.cst.controllers.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.cst.domain.Users.UserDTO;
import com.app.cst.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
     private final AuthService authService;

     @GetMapping("/login")
     public String loginPage() {
          return "pages/auth/login/index";
     }

     @GetMapping("/register")
     public String registerPage(Model model) {
          model.addAttribute("userDTO", new UserDTO("", "", ""));
          return "pages/auth/register/index";
     }

     @PostMapping("/register")
     public String registerPage(@Valid @ModelAttribute UserDTO userDTO, BindingResult result, Model model) {
          if (result.hasErrors()) {
               System.out.println("has errors");
               model.addAttribute("userDTO", userDTO);
               return "pages/auth/register/index";
          }
          this.authService.register(userDTO);
          return "redirect:/auth/login";
     }

}
