package com.abdallah.TechCart_Ecommerce.repo;

import com.abdallah.TechCart_Ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}