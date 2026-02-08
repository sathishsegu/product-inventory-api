package com.inventory.service;

import com.inventory.dto.PagedResponse;
import com.inventory.dto.ProductListResponseDTO;
import com.inventory.dto.ProductRequestDTO;
import com.inventory.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO dto);
    PagedResponse<ProductListResponseDTO> getAllProducts(int page, int size, String sortBy, String direction);
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);
    void deleteProduct(Long id);
}
