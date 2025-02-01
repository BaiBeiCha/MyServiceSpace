package com.baibei.myservicespace.repositories;

import com.baibei.myservicespace.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {

    Product findById(long id);
    Product findByName(String name);
    List<Product> findAllByPriceBetween(double min, double max);
    List<Product> findAllByCategory(String category);
    List<Product> findAllByCategoryAndPriceBetween(String category, double min, double max);

    void deleteById(long id);
}
