package com.inventory.service.impl;

import com.inventory.dto.PagedResponse;
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
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));

        Product product = modelMapper.map(dto, Product.class);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        ProductResponseDTO response =  modelMapper.map(savedProduct, ProductResponseDTO.class);
        response.setCategoryName(category.getCategoryName());

        return response;
    }

    @Override
    public PagedResponse<ProductListResponseDTO> getAllProducts(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductListResponseDTO> content = productPage.getContent()
                .stream()
                .map(product -> {
                    ProductListResponseDTO dto = new ProductListResponseDTO();
                    dto.setName(product.getName());
                    dto.setCategoryName(product.getCategory().getCategoryName());
                    return dto;
                })
                .toList();

        return new PagedResponse<>(
                content,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        return modelMapper.map(product, ProductResponseDTO.class);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {

        Product existingProduct = productRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException(
                                "Product not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException(
                                "Category not found with id: " + dto.getCategoryId()));

        modelMapper.map(dto, existingProduct);
        existingProduct.setCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);

        ProductResponseDTO response = modelMapper.map(updatedProduct, ProductResponseDTO.class);
        response.setCategoryName(category.getCategoryName());

        return response;
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        productRepository.delete(existingProduct);
    }

}
