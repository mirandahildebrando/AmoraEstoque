package com.AmoraEstoque.AmoraEstoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmoraEstoque.AmoraEstoque.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
