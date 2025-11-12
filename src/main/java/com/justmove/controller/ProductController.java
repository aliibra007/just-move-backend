package com.justmove.controller;

import com.justmove.model.Product;
import com.justmove.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get only in-stock products
    @GetMapping("/in-stock")
    public List<Product> getInStockProducts() {
        return productService.getInStockProducts();
    }

    // Get only out-of-stock products
    @GetMapping("/out-of-stock")
    public List<Product> getOutOfStockProducts() {
        return productService.getOutOfStockProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get products by category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    // Get in-stock products by category
    @GetMapping("/category/{category}/in-stock")
    public List<Product> getInStockProductsByCategory(@PathVariable String category) {
        return productService.getInStockProductsByCategory(category);
    }

    // Create new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Update existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails) {
        try {
            Product updated = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Toggle in-stock status
    @PatchMapping("/{id}/toggle-stock")
    public ResponseEntity<Product> toggleStockStatus(@PathVariable Long id) {
        try {
            Product updated = productService.toggleStockStatus(id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
@GetMapping("/search")
public ResponseEntity<List<Product>> searchProducts(
        @RequestParam(required = false, defaultValue = "") String q) {
    
    if (q == null || q.trim().isEmpty()) {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    List<Product> results = productService.searchProducts(q.trim());
    return ResponseEntity.ok(results);
}
}
