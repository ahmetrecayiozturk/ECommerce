package org.ecommerce.product.repository;

import org.ecommerce.product.dto.ProductDto;
import org.ecommerce.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
//burada biz MongoRepository'i kullanarak direkt onun methodlarını inheritance alırız
@Repository()
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findById(String id);
    Optional<Product> findIdByProductNameAndCategoryAndProductDescriptionAndProductPrice(String productName, String category, String productDescription, double productPrice);
    Page<Product> findProductsByCategory(String category, Pageable pageable);
    //Page<Product> findAll(Pageable pageable, Sort sort);

}
