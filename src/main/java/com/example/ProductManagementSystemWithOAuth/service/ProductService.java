package com.example.ProductManagementSystemWithOAuth.service;

import com.example.ProductManagementSystemWithOAuth.model.Product;
import com.example.ProductManagementSystemWithOAuth.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void removeProduct(long id) {
        productRepository.deleteById(id);
    }
    public Optional<Product> getProduct(long id) {
        return productRepository.findById(id);
    }
    public List<Product> getAllProductsByCategory_Id(Integer id) {
        return productRepository.findAllByCategory_Id(id);
    }
}
