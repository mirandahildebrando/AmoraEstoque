package com.AmoraEstoque.AmoraEstoque.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AmoraEstoque.AmoraEstoque.dto.LoginDTO;
import com.AmoraEstoque.AmoraEstoque.dto.RegisterDTO;
import com.AmoraEstoque.AmoraEstoque.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowCredentials = "false")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto, HttpSession session) {
        try {
            Long companyId = service.login(dto);

            
            session.setAttribute("companyId", companyId);

            return ResponseEntity.ok(companyId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); 
        return ResponseEntity.ok("Logout realizado");
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginDTO dto, HttpSession session) {
        try {
            String resultado = service.adminLogin(dto);
            session.setAttribute("companyId", 0L); 
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}