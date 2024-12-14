package com.ecommerce.ecommercebackend.ecommerce.dto;

import lombok.Data;
//güvenlik açığı vermemek için bu katmanı oluşturuyoruz, service ve controller ile bursaı iletişime geçecek
//burası ileyse entity iletişime geçecek
@Data
public class ProductDto {
    //private String productId;
    private String productName;
    private String productDescription;
    private double productPrice;


    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    //public String getProductId() {return productId;}

    //public void setProductId(String productId) {this.productId = productId;}

}
