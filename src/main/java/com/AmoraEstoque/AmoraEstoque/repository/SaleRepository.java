package com.AmoraEstoque.AmoraEstoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmoraEstoque.AmoraEstoque.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
