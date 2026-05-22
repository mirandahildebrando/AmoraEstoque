package com.AmoraEstoque.AmoraEstoque.controller;

import com.AmoraEstoque.AmoraEstoque.entity.Sale;
import com.AmoraEstoque.AmoraEstoque.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
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
            @RequestBody Sale sale,
            @RequestHeader(value = "companyId", required = false) String companyIdHeader) {
        try {
            Long companyId = getCompanyId(companyIdHeader);
            return ResponseEntity.ok(service.save(sale, companyId));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> list(
            @RequestHeader(value = "companyId", required = false) String companyIdHeader) {
        try {
            Long companyId = getCompanyId(companyIdHeader);
            return ResponseEntity.ok(service.listByCompany(companyId));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
