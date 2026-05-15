package com.AmoraEstoque.AmoraEstoque.dto;

import com.AmoraEstoque.AmoraEstoque.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {

    private String username;

    private String email;

    private String password;

    private String companyName;
}
