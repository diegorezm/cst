package com.app.cst.controllers.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.cst.domain.ProductEntity.ProductEntity;
import com.app.cst.domain.ProductEntity.ProductEntityDTO;
import com.app.cst.services.ProductEntityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/dashboard/products")
@RequiredArgsConstructor
public class ProductEntityController {
     private final ProductEntityService productEntityService;

     @GetMapping
     public String productsPage(Model model,
               @RequestParam(required = false) String keyword,
               @RequestParam("page") Optional<Integer> page,
               @RequestParam("size") Optional<Integer> size) {
          try {
               int currentPage = page.orElse(1);
               int pageSize = size.orElse(7);
               List<ProductEntity> productEntities = new ArrayList<>();
               Pageable paging = PageRequest.of(currentPage - 1, pageSize, Sort.by("id").ascending());

               Page<ProductEntity> productsPage;

               if (keyword == null || keyword.isBlank()) {
                    productsPage = this.productEntityService.get(paging);
               } else {
                    productsPage = this.productEntityService.get(keyword, paging);
               }

               productEntities = productsPage.getContent();

               model.addAttribute("products", productEntities);
               model.addAttribute("currentPage", productsPage.getNumber() + 1);
               model.addAttribute("pageSize", pageSize);
               model.addAttribute("totalItems", productsPage.getTotalElements());
               model.addAttribute("totalPages", productsPage.getTotalPages());
          } catch (Exception e) {
               model.addAttribute("message", e.getMessage());
          }
          return "pages/dashboard/products/index";
     }

     @GetMapping("/{id}")
     public String productPage(@PathVariable Integer id, Model model) {
          ProductEntity productEntity = this.productEntityService.get(id);
          model.addAttribute("entity", productEntity);
          model.addAttribute("products", productEntity.getProducts());
          return "pages/dashboard/products/product";
     }

     @GetMapping("/add")
     public String addPage(Model model) {
          model.addAttribute("payload", new ProductEntityDTO("", 0));
          return "pages/dashboard/products/add";
     }

     @GetMapping("/edit/{id}")
     public String editPage(@PathVariable Integer id, Model model) {
          try {
               ProductEntity productEntity = this.productEntityService.get(id);
               model.addAttribute("payload", new ProductEntityDTO(productEntity.getName(), productEntity.getPrice()));
               model.addAttribute("id", productEntity.getId());
               return "pages/dashboard/products/edit";
          } catch (Exception e) {
               System.err.println(e);
               return "redirect:/dashboard/products";
          }

     }

     @PostMapping("/edit/{id}")
     public String editPage(@PathVariable Integer id, Model model,
               @Valid @ModelAttribute("payload") ProductEntityDTO payload, BindingResult result) {
          if (result.hasErrors()) {
               return "pages/dashboard/products/edit";
          }
          try {
               this.productEntityService.update(id, payload);
               return "redirect:/dashboard/products";
          } catch (Exception e) {
               model.addAttribute("message", "Something went wrong!");
               System.err.println(e);
               return "pages/dashboard/products/edit";
          }

     }

     @PostMapping("/add")
     public String addPage(@Valid @ModelAttribute("payload") ProductEntityDTO payload, BindingResult result,
               Model model) {
          if (result.hasErrors()) {
               return "pages/dashboard/products/add";
          }

          try {
               this.productEntityService.create(payload);
          } catch (Exception e) {
               System.err.println(e);
               model.addAttribute("message", "Something went wrong!");
          }
          return "redirect:/dashboard/products";
     }

     @PostMapping("/delete/{id}")
     public String removeProductEntity(@PathVariable Integer id, Model model, HttpServletRequest request) {
          model.addAttribute("message", "Something went wrong!");
          try {
               this.productEntityService.delete(id);
          } catch (Exception e) {
               System.err.println(e);
               model.addAttribute("message", "Something went wrong!");
          }

          String referer = request.getHeader("Referer");
          return "redirect:" + (referer != null ? referer : "/dashboard/products");
     }
}
