package com.AmoraEstoque.AmoraEstoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmoraEstoque.AmoraEstoque.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
