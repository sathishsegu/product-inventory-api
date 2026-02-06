package com.inventory.service;

import com.inventory.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Optional<Product> updateProduct(Long id, Product updatedProduct);
    Optional<Product> deleteProduct(Long id);
}
