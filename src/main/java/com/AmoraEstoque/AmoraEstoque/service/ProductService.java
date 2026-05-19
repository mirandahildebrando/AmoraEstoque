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

    public ProductService(ProductRepository repository, LoggedUserService loggedUserService) {
        this.repository = repository;
        this.loggedUserService = loggedUserService;
    }

    public Product save(Product product, Long companyId) {
        product.setId(companyId);
        return repository.save(product);
    }

    public List<Product> list() {

        User user = loggedUserService.getUser();

        Long companyId = user.getCompany().getId();

        return repository.findByCompanyId(companyId);
    }

    public List<Product> listByCompany(Long companyId) {
        return repository.findByCompanyId(companyId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Product update(Long id, Product product) {
        Product existingProduct = repository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            return repository.save(existingProduct);
        } else {
            return null;
        }
        
    }
}
