package com.AmoraEstoque.AmoraEstoque.controller;

import org.springframework.web.bind.annotation.*;

import com.AmoraEstoque.AmoraEstoque.entity.Sale;
import com.AmoraEstoque.AmoraEstoque.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("/sales")
@CrossOrigin("*")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping
    public Sale save(@RequestBody Sale sale) {
        return service.save(sale);
    }

    @GetMapping
    public List<Sale> list() {
        return service.list();
    }
}
