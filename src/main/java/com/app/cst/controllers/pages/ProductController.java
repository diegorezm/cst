package com.app.cst.controllers.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.cst.domain.Products.Product;
import com.app.cst.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/dashboard/product")
@RequiredArgsConstructor
public class ProductController {
     private final ProductService productService;

     @PostMapping("/create/{entityId}")
     public String createProduct(@PathVariable Integer entityId, HttpServletRequest request, Model model) {
          try {
               this.productService.create(entityId);
          } catch (Exception e) {
               System.err.println(e);
               model.addAttribute("message", "Something went wrong!");
          }

          return "redirect:/dashboard/products/" + entityId;
     }

     @PostMapping("/sell/{id}")
     public String sellProduct(@PathVariable Integer id, HttpServletRequest request, Model model) {
          try {
               Product product = this.productService.get(id);
               this.productService.update(id, !product.getSold());
          } catch (Exception e) {
               System.err.println(e);
               model.addAttribute("message", "Something went wrong!");
          }

          String referer = request.getHeader("Referer");
          return "redirect:" + (referer != null ? referer : "/dashboard/products");
     }

     @PostMapping("/delete/{id}")
     public String deleteProduct(@PathVariable Integer id, HttpServletRequest request, Model model) {
          try {
               this.productService.delete(id);
          } catch (Exception e) {
               System.err.println(e);
               model.addAttribute("message", "Something went wrong!");
          }

          String referer = request.getHeader("Referer");
          return "redirect:" + (referer != null ? referer : "/dashboard/products");
     }
}
