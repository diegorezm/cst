package com.app.cst.controllers.pages;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.cst.domain.Users.User;
import com.app.cst.domain.Users.UserDTO;
import com.app.cst.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/dashboard/profile")
@RequiredArgsConstructor
public class ProfileController {
     private final UserService userService;

     @GetMapping
     public String profilePage(Model model) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          User user = (User) authentication.getPrincipal();
          model.addAttribute("payload", new UserDTO(user.getUsername(), user.getEmail(), ""));
          return "pages/dashboard/profile/index";
     }

     @PostMapping
     public String changeName(@ModelAttribute("payload") UserDTO payload, Model model) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          User user = (User) authentication.getPrincipal();
          if (payload.username() == "" || payload.username().length() < 4) {
               model.addAttribute("message", "Username has to be atleast 4 characters!");
               return "pages/dashboard/profile/index";
          }
          try {
               this.userService.update(user.getId(), payload.username());
          } catch (Exception e) {
               model.addAttribute("message", "Something went wrong!");
          }
          return "pages/dashboard/profile/index";
     }
}
