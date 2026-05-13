package com.AmoraEstoque.AmoraEstoque.controller;

import org.springframework.web.bind.annotation.*;

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
    public Product save(@RequestBody Product product) {
        
        return service.save(product);
    }

    @GetMapping
    public List<Product> list() {
        return service.list();
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,
                          @RequestBody Product product) {

        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
