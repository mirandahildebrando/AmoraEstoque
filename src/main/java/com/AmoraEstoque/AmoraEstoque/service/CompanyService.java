package com.AmoraEstoque.AmoraEstoque.service;

import com.AmoraEstoque.AmoraEstoque.entity.Company;
import com.AmoraEstoque.AmoraEstoque.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company save(Company company) {
        return repository.save(company);
    }

    public List<Company> list() {
        return repository.findAll();
    }

    public Company update(Long id, Company company) {

        Company existing = repository.findById(id)
                .orElseThrow();

        existing.setName(company.getName());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}