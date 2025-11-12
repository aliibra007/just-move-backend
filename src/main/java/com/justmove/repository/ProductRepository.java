package com.justmove.repository;

import com.justmove.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategory(String category);
     List<Product> findByInStock(Boolean inStock);
     List<Product> findByCategoryAndInStock(String category, Boolean inStock);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> searchByName(@Param("query") String query);
    List<Product> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
    String name, String category

);
}