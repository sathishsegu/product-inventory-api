package com.inventory.service.impl;

import com.inventory.dto.CategoryRequestDTO;
import com.inventory.dto.CategoryResponseDTO;
import com.inventory.entity.Category;
import com.inventory.exception.CategoryNotFoundException;
import com.inventory.repository.CategoryRepository;
import com.inventory.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        Category category = modelMapper.map(dto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryResponseDTO.class);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryResponseDTO.class))
                .toList();
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + categoryId));

        return modelMapper.map(category, CategoryResponseDTO.class);
    }
}
