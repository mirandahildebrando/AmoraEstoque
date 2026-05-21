package com.AmoraEstoque.AmoraEstoque.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AmoraEstoque.AmoraEstoque.entity.Product;
import com.AmoraEstoque.AmoraEstoque.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowCredentials = "false")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    
    private Long getCompanyId(HttpSession session) {
        Long companyId = (Long) session.getAttribute("companyId");
        if (companyId == null) {
            throw new IllegalStateException("Não autenticado. Faça login primeiro.");
        }
        return companyId;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product, HttpSession session) {
        try {
            Long companyId = getCompanyId(session);
            return ResponseEntity.ok(service.save(product, companyId));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> list(HttpSession session) {
        try {
            Long companyId = getCompanyId(session);
            return ResponseEntity.ok(service.list(companyId));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Product product,
            HttpSession session) {
        try {
            getCompanyId(session); 
            return ResponseEntity.ok(service.update(id, product));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpSession session) {
        try {
            getCompanyId(session); 
            service.delete(id);
            return ResponseEntity.ok("Produto deletado");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}