package com.abdallah.TechCart_Ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        String brand,
        BigDecimal price,
        String category,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        Date releaseDate,
        boolean productAvailable,
        int stockQuantity,
        String imageUrl
) {
}
