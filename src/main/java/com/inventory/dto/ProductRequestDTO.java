package com.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "Product name must not be empty")
    private String name;

    @NotNull(message = "Price is Required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity can't be negative")
    private Integer quantity;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}
