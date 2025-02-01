package com.baibei.myservicespace.controllers;

import com.baibei.myservicespace.models.Product;
import com.baibei.myservicespace.repositories.MessagesRepository;
import com.baibei.myservicespace.repositories.ProductsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductsRepository productsRepository;
    private final MessagesRepository messagesRepository;

    public AdminController(ProductsRepository productsRepository,
                           MessagesRepository messagesRepository) {
        this.productsRepository = productsRepository;
        this.messagesRepository = messagesRepository;
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productsRepository.findAll());
        return "admin-products";
    }

    @PostMapping("/products")
    public String addProduct(Product product) {
        productsRepository.save(product);
        return "redirect:/admin/products";
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable long id) {
        productsRepository.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/messages")
    public String messages(Model model) {
        model.addAttribute("messages", messagesRepository.findAll());
        return "admin-messages";
    }

    @DeleteMapping("/messages/{id}")
    public String deleteMessage(@PathVariable long id) {
        messagesRepository.deleteById(id);
        return "redirect:/admin/messages";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable long id, Model model) {
        model.addAttribute("product", productsRepository.findById(id));
        return "admin-product-edit";
    }

    @PutMapping("/products/edit/{id}")
    public String saveEditedProduct(@PathVariable long id, Product product) {
        if (productsRepository.existsById(id)) {
            product.setId(id);
            productsRepository.save(product);
        }

        return "redirect:/admin/products";
    }
}
