package com.AmoraEstoque.AmoraEstoque.service;

import org.springframework.stereotype.Service;

import com.AmoraEstoque.AmoraEstoque.entity.User;

import lombok.Getter;
import lombok.Setter;

@Service
public class LoggedUserService {

    private User loggedUser;

    public User getUser() {
        return loggedUser;
    }

    public void setUser(User user) {
        this.loggedUser = user;
    }



}
