package com.justmove.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> imageUrls;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> availableColors;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> availableSizes;

    @Column(name = "in_stock", nullable = false)
    private Boolean inStock = true;

    // Constructors
    public Product() {}

    public Product(String name, String category, String description, Double price,
                   List<String> imageUrls, List<String> availableColors, 
                   List<String> availableSizes, Boolean inStock) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageUrls = imageUrls;
        this.availableColors = availableColors;
        this.availableSizes = availableSizes;
        this.inStock = inStock;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }

    public List<String> getAvailableColors() { return availableColors; }
    public void setAvailableColors(List<String> availableColors) { this.availableColors = availableColors; }

    public List<String> getAvailableSizes() { return availableSizes; }
    public void setAvailableSizes(List<String> availableSizes) { this.availableSizes = availableSizes; }

    public Boolean getInStock() { return inStock; }
    public void setInStock(Boolean inStock) { this.inStock = inStock; }
}
