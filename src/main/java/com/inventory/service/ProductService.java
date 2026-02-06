package com.inventory.service;

import com.inventory.dto.ProductRequestDTO;
import com.inventory.dto.ProductResponseDTO;
import com.inventory.entity.Product;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO dto);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);
    boolean deleteProduct(Long id);
}
