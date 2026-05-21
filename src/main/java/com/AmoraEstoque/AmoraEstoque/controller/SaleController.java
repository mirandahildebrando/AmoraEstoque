package com.AmoraEstoque.AmoraEstoque.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AmoraEstoque.AmoraEstoque.entity.Sale;
import com.AmoraEstoque.AmoraEstoque.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
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
    public ResponseEntity<?> save(@RequestBody Sale sale, HttpSession session) {
        try {
            Long companyId = getCompanyId(session);
            return ResponseEntity.ok(service.save(sale, companyId));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> list(HttpSession session) {
        try {
            Long companyId = getCompanyId(session);
            return ResponseEntity.ok(service.listByCompany(companyId));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}