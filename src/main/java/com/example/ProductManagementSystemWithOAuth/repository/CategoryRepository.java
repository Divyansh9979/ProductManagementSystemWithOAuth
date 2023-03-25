package com.example.ProductManagementSystemWithOAuth.repository;

import com.example.ProductManagementSystemWithOAuth.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
