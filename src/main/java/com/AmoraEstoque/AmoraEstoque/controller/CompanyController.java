package com.AmoraEstoque.AmoraEstoque.controller;

import com.AmoraEstoque.AmoraEstoque.entity.Company;
import com.AmoraEstoque.AmoraEstoque.service.CompanyService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    public Company save(@RequestBody Company company) {
        return service.save(company);
    }

    @GetMapping
    public List<Company> list() {
        return service.list();
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable Long id,
                          @RequestBody Company company) {

        return service.update(id, company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}