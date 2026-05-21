package com.AmoraEstoque.AmoraEstoque.service;

import org.springframework.stereotype.Service;

import com.AmoraEstoque.AmoraEstoque.dto.LoginDTO;
import com.AmoraEstoque.AmoraEstoque.dto.RegisterDTO;
import com.AmoraEstoque.AmoraEstoque.entity.Role;
import com.AmoraEstoque.AmoraEstoque.entity.User;
import com.AmoraEstoque.AmoraEstoque.repository.CompanyRepository;
import com.AmoraEstoque.AmoraEstoque.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public AuthService(
            UserRepository userRepository,
            CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public String register(RegisterDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return "Usuário cadastrado";
    }

    public Long login(LoginDTO dto) {
        // 1. Busca o usuário
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // 2. Valida senha
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("Senha inválida");
        }

        // 3. ADMIN não precisa de empresa — retorna 0 imediatamente
        if (user.getRole() == Role.ADMIN) {
            return 0L;
        }

        // 4. Usuário comum precisa ter empresa
        if (user.getCompany() == null) {
            throw new IllegalStateException("Usuário sem empresa vinculada");
        }

        // 5. Empresa deve estar ativa
        if (!Boolean.TRUE.equals(user.getCompany().getActive())) {
            throw new IllegalStateException("Empresa bloqueada");
        }

        return user.getCompany().getId();
    }

    public String adminLogin(LoginDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("Senha inválida");
        }

        if (user.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Acesso negado");
        }

        return "Login admin realizado";
    }
}