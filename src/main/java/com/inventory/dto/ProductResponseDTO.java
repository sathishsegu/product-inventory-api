package com.inventory.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private LocalDateTime createdAt;
}
