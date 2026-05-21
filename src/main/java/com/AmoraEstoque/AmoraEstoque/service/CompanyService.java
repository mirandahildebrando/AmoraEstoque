package com.AmoraEstoque.AmoraEstoque.service;

import com.AmoraEstoque.AmoraEstoque.dto.CompanyResponseDTO;
import com.AmoraEstoque.AmoraEstoque.entity.Company;
import com.AmoraEstoque.AmoraEstoque.entity.Role;
import com.AmoraEstoque.AmoraEstoque.entity.User;
import com.AmoraEstoque.AmoraEstoque.repository.CompanyRepository;
import com.AmoraEstoque.AmoraEstoque.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    
    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public CompanyResponseDTO save(Company company) {
        
        company.setActive(true);

        
        Company savedCompany = companyRepository.save(company);

        
        String username = company.getName()
                .toLowerCase()
                .replaceAll("\\s+", "");

        
        User user = new User();
        user.setUsername(username);
        user.setEmail(company.getEmail());
        user.setPassword("123456"); 
        user.setRole(Role.EMPRESA);
        user.setCompany(savedCompany); 

        userRepository.save(user);

        
        return new CompanyResponseDTO(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getEmail(),
                username,
                "123456"
        );
    }

    public List<Company> list() {
        return companyRepository.findAll();
    }
}