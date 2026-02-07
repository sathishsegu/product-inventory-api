package com.inventory.service.impl;

import com.inventory.dto.ProductListResponseDTO;
import com.inventory.dto.ProductRequestDTO;
import com.inventory.dto.ProductResponseDTO;
import com.inventory.entity.Category;
import com.inventory.entity.Product;
import com.inventory.exception.CategoryNotFoundException;
import com.inventory.exception.ProductNotFoundException;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return mapToResponseDTO(savedProduct);
    }

    @Override
    public List<ProductListResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToListResponseDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        return mapToResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {

        Product existingProduct = productRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException(
                                "Product not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException(
                                "Category not found with id: " + dto.getCategoryId()));

        existingProduct.setName(dto.getName());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setQuantity(dto.getQuantity());
        existingProduct.setCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);

        return mapToResponseDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        productRepository.delete(existingProduct);
    }

    private Product mapToEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        return product;
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setCategoryName(product.getCategory().getCategoryName());
        return dto;
    }

    private ProductListResponseDTO mapToListResponseDTO(Product product) {
        ProductListResponseDTO dto = new ProductListResponseDTO();
        dto.setName(product.getName());
        dto.setCategoryName(product.getCategory().getCategoryName());
        return dto;
    }
}
