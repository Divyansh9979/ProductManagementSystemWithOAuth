package com.example.ProductManagementSystemWithOAuth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;
    @NotNull(message = "Category ID is required")
    private int categoryId;
    @NotNull(message = "Price is required")
    @Positive(message = "Price should be positive")
    private Double price;
    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private Double weight;
    private String description;
    private String imageName;
}
