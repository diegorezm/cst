package com.app.cst.controllers.api;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cst.domain.Transactions.ReportDTO;
import com.app.cst.domain.Transactions.Transaction;
import com.app.cst.services.TransactionService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/transactions")
@RestController
@RequiredArgsConstructor
public class ApiTransactionController {
     private final TransactionService transactionService;

     @PostMapping("/report")
     public void report(@Valid @RequestBody ReportDTO reportDTO, HttpServletResponse response) {
          LocalDateTime start = reportDTO.start().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
          LocalDateTime end = reportDTO.end().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
          List<Transaction> transactions = this.transactionService.get(start, end);

          response.setContentType("application/pdf");
          response.setHeader("Content-Disposition", "inline; filename=\"transactions_report.pdf\"");
          Document document = new Document();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

          try {
               PdfWriter.getInstance(document, response.getOutputStream());
               document.open();

               Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
               Paragraph title = new Paragraph("Transactions Report", titleFont);
               title.setAlignment(Element.ALIGN_CENTER);
               document.add(title);

               Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);

               document.add(new Paragraph("From: " + start.format(formatter).toString(), subtitleFont));
               document.add(new Paragraph("To: " + start.format(formatter).toString(), subtitleFont));
               document.add(new Paragraph("\n"));

               PdfPTable table = new PdfPTable(4);

               table.setWidths(new float[] { 1, 3, 2, 2 });

               table.addCell("ID");
               table.addCell("Product");
               table.addCell("Amount");
               table.addCell("Date");

               for (Transaction transaction : transactions) {
                    table.addCell(String.valueOf(transaction.getId()));
                    table.addCell(transaction.getProduct().getProductEntity().getName());
                    table.addCell(String.valueOf(transaction.getProduct().getProductEntity().getPrice()));
                    table.addCell(transaction.getDate().format(formatter)); // Format date
               }

               document.add(table);
               document.close();
          } catch (Exception e) {
               System.err.println(e);
          }
     }

}
