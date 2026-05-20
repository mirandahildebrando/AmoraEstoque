package com.AmoraEstoque.AmoraEstoque.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.AmoraEstoque.AmoraEstoque.entity.Product;
import com.AmoraEstoque.AmoraEstoque.entity.User;
import com.AmoraEstoque.AmoraEstoque.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    private final LoggedUserService loggedUserService;

    public ProductService(ProductRepository repository,
                          LoggedUserService loggedUserService) {

        this.repository = repository;
        this.loggedUserService = loggedUserService;
    }

    public Product save(Product product) {

        User user = loggedUserService.getUser();

        product.setCompany(user.getCompany());

        return repository.save(product);
    }

    public List<Product> list() {

        User user = loggedUserService.getUser();

        Long companyId = user.getCompany().getId();

        return repository.findByCompanyId(companyId);
    }

    public Product update(Long id, Product product) {

        User user = loggedUserService.getUser();

        Product existingProduct = repository.findById(id).orElseThrow(() -> 
        new RuntimeException("Produto não encontrado"));

        if (!existingProduct.getCompany().getId().equals(user.getCompany().getId())) {
            throw new RuntimeException("Produto não pertence à empresa");
        }

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());

        return repository.save(existingProduct);
    }

        public void delete(Long id) {

            User user = loggedUserService.getUser();

            Product product = repository.findById(id).orElseThrow(() -> 
            new RuntimeException("Produto não encontrado"));
            if (!product.getCompany().getId().equals(user.getCompany().getId())) {
                throw new RuntimeException("Produto não pertence à empresa");
            }

        repository.deleteById(id);
    }
}