package com.example.ProductManagementSystemWithOAuth.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class  Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long product_id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;
    private Double price;
    private Double weight;
    private String description;
    private String imageName;

}
