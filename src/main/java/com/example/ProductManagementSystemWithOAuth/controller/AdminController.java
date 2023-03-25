package com.example.ProductManagementSystemWithOAuth.controller;

import com.example.ProductManagementSystemWithOAuth.dto.ProductDTO;
import com.example.ProductManagementSystemWithOAuth.model.Category;
import com.example.ProductManagementSystemWithOAuth.model.Product;
import com.example.ProductManagementSystemWithOAuth.service.CategoryService;
import com.example.ProductManagementSystemWithOAuth.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@Slf4j
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/productImages";

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    //Category Section
    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model model) {
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable Integer id,Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()){
            model.addAttribute("category",category.get());
            return "categoriesAdd";
        } else
            return "404";
    }

    //Product Section
    @GetMapping("/admin/products")
    public String getProducts(Model model) {
        model.addAttribute("products",productService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProducts(Model model) {
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String postProducts(@ModelAttribute("productDTO") ProductDTO productDTO,
                               @RequestParam("productImage") MultipartFile file,
                               @RequestParam("imgName") String imgName) throws IOException {
        try {
            Product product1 = new Product();
            product1.setProduct_id(productDTO.getId());
            product1.setName(productDTO.getName());
            product1.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
            product1.setPrice(productDTO.getPrice());
            product1.setWeight(productDTO.getWeight());
            product1.setDescription(productDTO.getDescription());
            String imageUUID;
            if(!file.isEmpty()) {
                imageUUID = file.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
                Files.write(fileNameAndPath,file.getBytes());
            } else {
                imageUUID = imgName;
            }
            product1.setImageName(imageUUID);
            productService.saveProduct(product1);
            return "redirect:/admin/products";
        } catch (Exception e) {
            log.error("Product Not Added...");
            e.printStackTrace();
            return "404";
        }
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.removeProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable("id") long id, Model model) {
        Product product = productService.getProduct(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getProduct_id());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("productDTO",productDTO);

        return "productsAdd";
    }

}
