package com.baibei.myservicespace.controllers.api;

import com.baibei.myservicespace.dto.ProductCard;
import com.baibei.myservicespace.models.Product;
import com.baibei.myservicespace.repositories.ProductsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping
    public List<Product> getUsers() {
        return (List<Product>) productsRepository.findAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductCard card) {
        Product product = new Product(card);
        productsRepository.save(product);
        return product;
    }

    @PatchMapping
    public Product updateProduct(@RequestBody ProductCard card) {
        Product product = productsRepository.findByName(card.getName());

        if (product != null) {
            if (card.getCategory() != null) {
                product.setCategory(card.getCategory());
            }
            if (card.getDescription() != null) {
                product.setDescription(card.getDescription());
            }
            if (card.getImage() != null) {
                product.setImage(card.getImage());
            }
            if (card.getPrice() > 0) {
                product.setPrice(card.getPrice());
            }
        } else {
            product = new Product(card);
        }

        productsRepository.save(product);
        return product;
    }

    @DeleteMapping("/{name}")
    public String deleteProduct(@PathVariable String name) {
        Product product = productsRepository.findByName(name);

        if (product != null) {
            productsRepository.delete(product);
            return "Successfully deleted product " + name;
        } else {
            return "Product " + name + " not found";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id) {
        Product product = productsRepository.findById(id);

        if (product != null) {
            productsRepository.delete(product);
            return "Successfully deleted product " + id;
        } else {
            return "Product " + id + " not found";
        }
    }
}
