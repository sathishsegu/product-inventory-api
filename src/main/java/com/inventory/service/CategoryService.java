package com.inventory.service;

import com.inventory.dto.CategoryRequestDTO;
import com.inventory.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO dto);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO getCategoryById(Long categoryId);
}
