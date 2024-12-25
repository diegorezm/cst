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
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.cst.domain.Transactions.Transaction;
import com.app.cst.services.TransactionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/dashboard/transactions")
@RequiredArgsConstructor
public class TransactionController {
     private final TransactionService transactionService;

     @GetMapping
     public String indexPage(Model model, @RequestParam("page") Optional<Integer> page,
               @RequestParam("size") Optional<Integer> size) {
          try {
               int currentPage = page.orElse(1);
               int pageSize = size.orElse(7);
               List<Transaction> transactions = new ArrayList<>();
               Pageable paging = PageRequest.of(currentPage - 1, pageSize, Sort.by("date").descending());
               Page<Transaction> transactionsPage = this.transactionService.get(paging);
               transactions = transactionsPage.getContent();

               model.addAttribute("transactions", transactions);
               model.addAttribute("currentPage", transactionsPage.getNumber() + 1);
               model.addAttribute("pageSize", pageSize);
               model.addAttribute("totalItems", transactionsPage.getTotalElements());
               model.addAttribute("totalPages", transactionsPage.getTotalPages());
          } catch (Exception e) {
               model.addAttribute("message", e.getMessage());
          }
          return "pages/dashboard/transactions/index";
     }

}
