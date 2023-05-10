package com.example.image_chat_system_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.image_chat_system_api.domain.UserList;

@Repository
public interface UserLoginRepository extends JpaRepository<UserList, Integer>{
    public Optional<UserList> findByUserMail(String mail);   
}