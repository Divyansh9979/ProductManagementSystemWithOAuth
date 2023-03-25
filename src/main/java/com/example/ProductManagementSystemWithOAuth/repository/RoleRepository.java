package com.example.ProductManagementSystemWithOAuth.repository;

import com.example.ProductManagementSystemWithOAuth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
