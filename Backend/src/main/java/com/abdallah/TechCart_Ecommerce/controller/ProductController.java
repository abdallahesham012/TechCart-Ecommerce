package com.abdallah.TechCart_Ecommerce.controller;

import com.abdallah.TechCart_Ecommerce.dto.ProductRequest;
import com.abdallah.TechCart_Ecommerce.dto.ProductResponse;
import com.abdallah.TechCart_Ecommerce.mapper.ProductMapper;
import com.abdallah.TechCart_Ecommerce.model.Product;
import com.abdallah.TechCart_Ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    final private ProductService productService;
    final private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping()
    public List<ProductResponse> getProducts() {
        return productService.getAllProducts().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword).stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable int id) {
        return productMapper.toResponse(productService.getProductByID(id));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id) {
        Product product = productService.getProductByID(id);
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.valueOf(product.getImageType()))
                .body(product.getImageData());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponse> addProduct(
            @Valid @RequestPart("product") ProductRequest productRequest,
            @RequestPart("imageFile") MultipartFile imageFile) throws Exception {

        Product savedProduct = productService.addProduct(productMapper.toEntity(productRequest), imageFile);
        return new ResponseEntity<>(productMapper.toResponse(savedProduct), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable int id,
            @Valid @RequestPart("product") ProductRequest productRequest,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws Exception {

        Product updated = productService.updateProduct(id, productMapper.toEntity(productRequest), imageFile);
        return new ResponseEntity<>(productMapper.toResponse(updated), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }


}
