package com.AmoraEstoque.AmoraEstoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmoraEstoque.AmoraEstoque.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
