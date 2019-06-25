package com.jyoon.server.springcommunity.repository;


import com.jyoon.server.springcommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
