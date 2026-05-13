package com.AmoraEstoque.AmoraEstoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmoraEstoque.AmoraEstoque.entity.SaleItem;

public interface SaleItemRepository
        extends JpaRepository<SaleItem, Long> {
}