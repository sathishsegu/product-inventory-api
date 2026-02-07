package com.inventory.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {

    private String name;
    private Double price;
    private Integer quantity;
    private Long categoryId;
}
