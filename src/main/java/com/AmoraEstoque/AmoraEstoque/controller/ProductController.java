package com.AmoraEstoque.AmoraEstoque.controller;

import org.springframework.web.bind.annotation.*;

import com.AmoraEstoque.AmoraEstoque.entity.Company;
import com.AmoraEstoque.AmoraEstoque.entity.Product;
import com.AmoraEstoque.AmoraEstoque.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product save(@RequestBody Product product, @RequestHeader("companyId") Long companyId) {

        Company company = new Company();
        product.setId(companyId);
        product.setCompany(company);
        return service.save(product);
    }

    @GetMapping
    public List<Product> list(@RequestHeader("companyId") Long companyId) {
        return service.listByCompany(companyId);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,
                          @RequestBody Product product, @RequestHeader("companyId") Long companyId) {

            Company company = new Company();
            product.setId(companyId);
            product.setCompany(company);
            return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
