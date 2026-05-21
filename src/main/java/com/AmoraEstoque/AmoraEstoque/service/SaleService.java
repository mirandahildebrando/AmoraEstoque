package com.AmoraEstoque.AmoraEstoque.service;

import org.springframework.stereotype.Service;

import com.AmoraEstoque.AmoraEstoque.entity.Company;
import com.AmoraEstoque.AmoraEstoque.entity.Product;
import com.AmoraEstoque.AmoraEstoque.entity.Sale;
import com.AmoraEstoque.AmoraEstoque.entity.SaleItem;
import com.AmoraEstoque.AmoraEstoque.repository.ProductRepository;
import com.AmoraEstoque.AmoraEstoque.repository.SaleRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    public Sale save(Sale sale, Long companyId) {
        // Vincula a empresa na venda via sessão — não depende do front
        Company company = new Company();
        company.setId(companyId);
        sale.setCompany(company);

        BigDecimal total = BigDecimal.ZERO;

        for (SaleItem item : sale.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduct().getId()));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Estoque insuficiente para: " + product.getName());
            }

            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

            item.setPrice(product.getPrice());
            item.setSale(sale);
            total = total.add(subtotal);

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        sale.setCreatedAt(LocalDateTime.now());
        sale.setTotal(total);

        return saleRepository.save(sale);
    }

    public List<Sale> listByCompany(Long companyId) {
        return saleRepository.findByCompanyId(companyId);
    }
}