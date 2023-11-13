package com.example.stockportfoliotracker.repository;

import com.example.stockportfoliotracker.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
