package com.justmove.service;

import com.justmove.model.Product;
import com.justmove.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getInStockProducts() {
        return productRepository.findByInStock(true);
    }

    public List<Product> getOutOfStockProducts() {
        return productRepository.findByInStock(false);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    /*public List<Product> searchProducts(String query) {
        return productRepository.searchByName(query);
    } */

    public List<Product> getInStockProductsByCategory(String category) {
        return productRepository.findByCategoryAndInStock(category, true);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setImageUrls(productDetails.getImageUrls());
        product.setAvailableColors(productDetails.getAvailableColors());
        product.setAvailableSizes(productDetails.getAvailableSizes());
        product.setInStock(productDetails.getInStock());

        return productRepository.save(product);
    }
    public Product toggleStockStatus(Long id) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    product.setInStock(!product.getInStock());
    return productRepository.save(product);
}

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
 public List<Product> searchProducts(String query) {
    return productRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
        query, query
    );
}
}

