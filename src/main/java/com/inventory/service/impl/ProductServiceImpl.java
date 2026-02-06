package com.inventory.service.impl;

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setQuantity(updatedProduct.getQuantity());

                    return productRepository.save(existingProduct);
                });
    }

    @Override
    public Optional<Product> deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return product;
                });
    }
}
