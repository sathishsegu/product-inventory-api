package com.inventory.service.impl;

import com.inventory.dto.ProductRequestDTO;
import com.inventory.dto.ProductResponseDTO;
import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = mapToEntity(dto);
        Product savedProduct = productRepository.save(product);
        return mapToResponseDTO(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return null;
        }
        return mapToResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if(existingProduct == null) {
            return null;
        }

        existingProduct.setName(dto.getName());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setQuantity(dto.getQuantity());

        Product updatedProduct = productRepository.save(existingProduct);

        return mapToResponseDTO(updatedProduct);
    }

    @Override
    public boolean deleteProduct(Long id) {
        if(!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
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
        return dto;
    }
}
