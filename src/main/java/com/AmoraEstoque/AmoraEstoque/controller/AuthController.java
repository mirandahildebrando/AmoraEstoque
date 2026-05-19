package com.AmoraEstoque.AmoraEstoque.controller;

import org.springframework.web.bind.annotation.*;

import com.AmoraEstoque.AmoraEstoque.dto.LoginDTO;
import com.AmoraEstoque.AmoraEstoque.dto.RegisterDTO;
import com.AmoraEstoque.AmoraEstoque.service.AuthService;
import com.AmoraEstoque.AmoraEstoque.service.LoggedUserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService service;

    private final LoggedUserService loggedUserService;

    public AuthController(AuthService service, LoggedUserService loggedUserService) {
        this.service = service;
        this.loggedUserService = loggedUserService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto) {
        return service.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return service.login(dto);
    }

    @PostMapping("/admin/login")
    public String adminLogin(@RequestBody LoginDTO dto) {
        return service.adminLogin(dto);
    }
}
