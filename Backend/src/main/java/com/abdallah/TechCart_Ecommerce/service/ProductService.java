package com.abdallah.TechCart_Ecommerce.service;

import com.abdallah.TechCart_Ecommerce.exception.ResourceNotFoundException;
import com.abdallah.TechCart_Ecommerce.model.Product;
import com.abdallah.TechCart_Ecommerce.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private static final long MAX_IMAGE_SIZE_BYTES = 10 * 1024 * 1024;
    private static final Set<String> ALLOWED_IMAGE_TYPES = Set.of(
            "image/jpeg", "image/png", "image/webp"
    );

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

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        validateImage(imageFile, true);
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product updatedProduct, MultipartFile imageFile) throws IOException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setReleaseDate(updatedProduct.getReleaseDate());
        existingProduct.setProductAvailable(updatedProduct.isProductAvailable());
        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());

        if (imageFile != null && !imageFile.isEmpty()) {
            validateImage(imageFile, false);
            existingProduct.setImageName(imageFile.getOriginalFilename());
            existingProduct.setImageType(imageFile.getContentType());
            existingProduct.setImageData(imageFile.getBytes());
        }

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        productRepository.delete(product);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }

    private void validateImage(MultipartFile imageFile, boolean required) {
        if (imageFile == null || imageFile.isEmpty()) {
            if (required) {
                throw new IllegalArgumentException("Product image is required");
            }
            return;
        }

        String contentType = imageFile.getContentType();
        if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("Image must be a JPEG, PNG, or WEBP file");
        }

        if (imageFile.getSize() > MAX_IMAGE_SIZE_BYTES) {
            throw new IllegalArgumentException("Image size must not exceed 10 MB");
        }
    }
}
