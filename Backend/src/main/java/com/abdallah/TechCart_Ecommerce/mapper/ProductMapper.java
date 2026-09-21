package com.abdallah.TechCart_Ecommerce.mapper;

import com.abdallah.TechCart_Ecommerce.dto.ProductRequest;
import com.abdallah.TechCart_Ecommerce.dto.ProductResponse;
import com.abdallah.TechCart_Ecommerce.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setBrand(productRequest.brand());
        product.setPrice(productRequest.price());
        product.setCategory(productRequest.category());
        product.setReleaseDate(productRequest.releaseDate());
        product.setProductAvailable(productRequest.productAvailable());
        product.setStockQuantity(productRequest.stockQuantity());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getCategory(),
                product.getReleaseDate(),
                product.isProductAvailable(),
                product.getStockQuantity(),
                "/api/products/" + product.getId() + "/image"
        );
    }
}
