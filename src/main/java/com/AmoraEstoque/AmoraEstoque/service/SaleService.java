package com.AmoraEstoque.AmoraEstoque.service;

import org.springframework.stereotype.Service;

import com.AmoraEstoque.AmoraEstoque.entity.Sale;
import com.AmoraEstoque.AmoraEstoque.repository.SaleRepository;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository repository;

    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }

    public Sale save(Sale sale) {
        return repository.save(sale);
    }

    public List<Sale> list() {
        return repository.findAll();
    }
}
