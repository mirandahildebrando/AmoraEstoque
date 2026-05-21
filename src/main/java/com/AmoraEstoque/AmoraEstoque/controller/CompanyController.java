package com.AmoraEstoque.AmoraEstoque.controller;

import com.AmoraEstoque.AmoraEstoque.dto.CompanyResponseDTO;
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
    public CompanyResponseDTO save(@RequestBody Company company) {
        return service.save(company);
    }

    @GetMapping
    public List<Company> list() {
        return service.list();
    }
}