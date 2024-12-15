package com.ecommerce.ecommercebackend.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//güvenlik açığı vermemek için bu katmanı oluşturuyoruz, service ve controller ile burası iletişime geçecek
//burası ileyse entity iletişime geçecek
@Data
public class ProductDto {
    //Bazı validasyonlar ile kontolleri sağlıyoruz
    @NotEmpty(message = "Product name cannot be empty")
    private String productName;

    @NotEmpty(message = "Product description cannot be empty")
    private String productDescription;

    @NotNull(message = "Product price cannot be null")
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
