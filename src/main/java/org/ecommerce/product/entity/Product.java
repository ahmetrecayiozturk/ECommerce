package org.ecommerce.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data//lombok ile getter setter yapılıyor
@Document(collection = "ecommerceproducts")
public class Product {
    @Id
    private String id;
    private String productName;
    private String productDescription;
    private String category;
    private double productPrice;
    private LocalDateTime productCreatedDate;

    public Product() {
        this.productCreatedDate = LocalDateTime.now(); // Oluşturma zamanını ayarlama
    }

}