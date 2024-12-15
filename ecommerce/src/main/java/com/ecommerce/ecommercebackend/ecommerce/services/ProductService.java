package com.ecommerce.ecommercebackend.ecommerce.services;

import com.ecommerce.ecommercebackend.ecommerce.dto.ProductDto;
import com.ecommerce.ecommercebackend.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(String productId);
    ProductDto addProduct(ProductDto productDto);
    ProductDto updateProduct(String productId, ProductDto productDto);
    void deleteProduct(String productId);

}
