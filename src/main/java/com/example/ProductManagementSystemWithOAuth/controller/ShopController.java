package com.example.ProductManagementSystemWithOAuth.controller;

import com.example.ProductManagementSystemWithOAuth.service.CategoryService;
import com.example.ProductManagementSystemWithOAuth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping({"/","/home"})
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("products",productService.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable("id") int id) {
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("products",productService.getAllProductsByCategory_Id(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable("id") int id) {
        model.addAttribute("product",productService.getProduct(id).get());
        return "viewProduct";
    }

}
