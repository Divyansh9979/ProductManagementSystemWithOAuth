package com.example.ProductManagementSystemWithOAuth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class  Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long product_id;
    @NotBlank(message = "Product name is required")
    private String name;
    @NotNull(message = "Category is required")
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;
    @NotNull(message = "Weight is required")
    private Double weight;
    private String description;
    private String imageName;

}
