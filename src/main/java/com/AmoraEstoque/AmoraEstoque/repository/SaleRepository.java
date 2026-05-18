package com.AmoraEstoque.AmoraEstoque.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmoraEstoque.AmoraEstoque.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Sale> findByCompanyId(Long companyId);
}
