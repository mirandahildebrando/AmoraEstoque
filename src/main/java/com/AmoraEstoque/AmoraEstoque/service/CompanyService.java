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

    // Injeta os dois repositórios
    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public CompanyResponseDTO save(Company company) {
        // 1. Garante que a empresa começa ativa
        company.setActive(true);

        // 2. Salva a empresa no banco
        Company savedCompany = companyRepository.save(company);

        // 3. Gera o username a partir do nome da empresa
        // Ex: "Loja da Maria" -> "lojadamaria"
        String username = company.getName()
                .toLowerCase()
                .replaceAll("\\s+", "");

        // 4. Cria o usuário da empresa automaticamente
        User user = new User();
        user.setUsername(username);
        user.setEmail(company.getEmail());
        user.setPassword("123456"); // senha padrão
        user.setRole(Role.EMPRESA);
        user.setCompany(savedCompany); // vínculo com a empresa

        userRepository.save(user);

        // 5. Retorna um DTO com os dados úteis para o admin
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