package com.AmoraEstoque.AmoraEstoque.service;

import org.springframework.stereotype.Service;

import com.AmoraEstoque.AmoraEstoque.entity.User;

@Service
public class LoggedUserService {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}