package com.AmoraEstoque.AmoraEstoque.service;

import org.springframework.stereotype.Service;

import com.AmoraEstoque.AmoraEstoque.dto.LoginDTO;
import com.AmoraEstoque.AmoraEstoque.dto.RegisterDTO;
import com.AmoraEstoque.AmoraEstoque.entity.Company;
import com.AmoraEstoque.AmoraEstoque.entity.Role;
import com.AmoraEstoque.AmoraEstoque.entity.User;
import com.AmoraEstoque.AmoraEstoque.repository.CompanyRepository;
import com.AmoraEstoque.AmoraEstoque.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public AuthService(UserRepository userRepository,
                       CompanyRepository companyRepository) {

        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public String register(RegisterDTO dto) {

        Company company = new Company();

        company.setName(dto.getCompanyName());

        company.setActive(true);

        companyRepository.save(company);

        User user = new User();

        user.setUsername(dto.getUsername());

        user.setEmail(dto.getEmail());

        user.setPassword(dto.getPassword());

        user.setRole(Role.EMPRESA);

        user.setCompany(company);

        userRepository.save(user);

        return "Usuário cadastrado";
    }

    public String login(LoginDTO dto) {

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        if (!user.getCompany().getActive()) {
            throw new RuntimeException("Empresa bloqueada");
        }

        return "Login realizado";
    }

    public String adminLogin(LoginDTO dto) {

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Admin não encontrado"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Acesso negado");
        }

        return "Login admin realizado";
    }
}