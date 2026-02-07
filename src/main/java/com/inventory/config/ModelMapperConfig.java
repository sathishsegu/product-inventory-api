package com.inventory.config;

import com.inventory.dto.ProductListResponseDTO;
import com.inventory.dto.ProductResponseDTO;
import com.inventory.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);

        modelMapper.createTypeMap(Product.class, ProductResponseDTO.class)
                .addMappings(mapper -> {
                    mapper.map(
                            src -> src.getCategory().getCategoryName(),
                            ProductResponseDTO::setCategoryName
                    );
                    mapper.map(
                            Product::getCreatedAt,
                            ProductResponseDTO::setCreatedAt
                    );
                });

        modelMapper.createTypeMap(Product.class, ProductListResponseDTO.class)
                .addMappings(mapper ->
                        mapper.map(
                                src -> src.getCategory().getCategoryName(),
                                ProductListResponseDTO::setCategoryName
                        )
                );

        return modelMapper;
    }
}
