package com.example.stockportfoliotracker.repository;

import com.example.stockportfoliotracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
