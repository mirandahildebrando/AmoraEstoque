package com.AmoraEstoque.AmoraEstoque.controller;

import com.AmoraEstoque.AmoraEstoque.entity.Product;
import com.AmoraEstoque.AmoraEstoque.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    private Long getCompanyId(String companyIdHeader) {
        if (companyIdHeader == null || companyIdHeader.isEmpty()) {
            throw new IllegalStateException("Não autenticado");
        }
        return Long.parseLong(companyIdHeader);
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody Product product,
            @RequestHeader(value = "companyId", required = false) String companyIdHeader) {
        try {
            Long companyId = getCompanyId(companyIdHeader);
            return ResponseEntity.ok(service.save(product, companyId));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> list(
            @RequestHeader(value = "companyId", required = false) String companyIdHeader) {
        try {
            Long companyId = getCompanyId(companyIdHeader);
            return ResponseEntity.ok(service.list(companyId));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Product product,
            @RequestHeader(value = "companyId", required = false) String companyIdHeader) {
        try {
            getCompanyId(companyIdHeader);
            return ResponseEntity.ok(service.update(id, product));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            @RequestHeader(value = "companyId", required = false) String companyIdHeader) {
        try {
            getCompanyId(companyIdHeader);
            service.delete(id);
            return ResponseEntity.ok("Produto deletado");
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
