package com.example.ProductManagementSystemWithOAuth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int id;
    @NotNull(message = "Category name is required")
    @Column(nullable = false)
    private String name;
}
