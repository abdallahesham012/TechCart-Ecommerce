package com.abdallah.TechCart_Ecommerce.service;

import com.abdallah.TechCart_Ecommerce.exception.ResourceNotFoundException;
import com.abdallah.TechCart_Ecommerce.model.Product;
import com.abdallah.TechCart_Ecommerce.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    final private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Product getProductByID(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
}