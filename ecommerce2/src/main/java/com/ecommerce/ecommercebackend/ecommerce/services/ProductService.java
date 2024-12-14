package com.ecommerce.ecommercebackend.ecommerce.services;

import com.ecommerce.ecommercebackend.ecommerce.dto.ProductDto;
import com.ecommerce.ecommercebackend.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto getProductById(String productId);
    public ProductDto addProduct(ProductDto productDto);
    public ProductDto updateProduct(String productId, ProductDto productDto);
    public void deleteProduct(String productId);
}
