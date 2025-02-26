package org.ecommerce.product.service;

import org.ecommerce.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    //sayfaya göre tüm productların döndürülmesi
    Page<ProductDto> getAllProducts(Pageable pageable);
    //id'ye göre product getirme
    ProductDto getProductById(String productId);
    //product ekleme(birden çok resim eklenebiliniyor)
    ProductDto addProduct(ProductDto productDto, List<MultipartFile> files);
    //product güncelleme
    ProductDto updateProduct(String productId, ProductDto productDto);
    //product silme
    void deleteProduct(String productId);
    //product idyi detaylar ile bulma
    String findProductIdByDetails(ProductDto productDto);
    //kategoriye göre listeleme
    Page<ProductDto> listProductsByCategory(String category, Pageable pageable);
    //filtreye göre listeleme
    Page<ProductDto> listProductsBySortOrder(String sortOrder, Pageable pageable);

}
