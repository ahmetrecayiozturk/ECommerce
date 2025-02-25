package org.ecommerce.product.service;

import org.ecommerce.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(String productId);
    ProductDto addProduct(ProductDto productDto);
    ProductDto updateProduct(String productId, ProductDto productDto);
    void deleteProduct(String productId);
    String findProductIdByDetails(ProductDto productDto);
    //kategoriye göre listeleme
    Page<ProductDto> listProductsByCategory(String category, Pageable pageable);
    //filtreye göre listeleme
    Page<ProductDto> listProductsBySortOrder(String sortOrder, Pageable pageable);

}
