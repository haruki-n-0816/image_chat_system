package com.example.image_chat_system_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.image_chat_system_api.domain.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin,String> {
    Optional<UserLogin> findByUserMail(String mail);
}
