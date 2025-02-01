package com.baibei.myservicespace.controllers;

import com.baibei.myservicespace.dto.FiltersCard;
import com.baibei.myservicespace.models.Product;
import com.baibei.myservicespace.repositories.ProductsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductsRepository productsRepository;

    public ShopController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping
    public String shop(Model model, @ModelAttribute("filtersCard") FiltersCard filtersCard) {
        List<Product> products;

        filtersCard.make();

        products = productsRepository.findAllByPriceBetween(
                filtersCard.getMinPrice(),
                filtersCard.getMaxPrice()
        );

        if (filtersCard.getCategories() != null && filtersCard.getCategories().length > 0) {
            products = products.stream()
                    .filter(p -> Arrays.asList(filtersCard.getCategories()).contains(p.getCategory()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("products", products);

        if (products.isEmpty()) {
            model.addAttribute("message", "No products found");
        }

        return "shop";
    }
}
