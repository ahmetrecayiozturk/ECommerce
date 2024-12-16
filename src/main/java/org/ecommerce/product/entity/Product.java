package org.ecommerce.product.entity;

//import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;


//@Entity
@Document(collection = "ecommerceproducts")
public class Product {
    @Id
    private String id;
    private String productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private LocalDateTime productCreatedDate;

    public Product() {
        this.productId = UUID.randomUUID().toString(); // Benzersiz UUID oluşturma
        this.productCreatedDate = LocalDateTime.now(); // Oluşturma zamanını ayarlama
    }

    public LocalDateTime getProductCreatedDate() {return productCreatedDate;}

    public void setProductCreatedDate(LocalDateTime productCreatedDate) {this.productCreatedDate = productCreatedDate;}

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId   = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

}
