package com.ecommerce.ecommercebackend.ecommerce.controllers;

import com.ecommerce.ecommercebackend.ecommerce.dto.ProductDto;
import com.ecommerce.ecommercebackend.ecommerce.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping("save")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        try {
            ProductDto savedProduct = productServiceImpl.addProduct(productDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProduct = productServiceImpl.updateProduct(productId, productDto);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@RequestBody ProductDto productDto) {
        try {
            String productId = productServiceImpl.findProductIdByDetails(
                    productDto.getProductName(),
                    productDto.getProductDescription(),
                    productDto.getProductPrice());
            productServiceImpl.deleteProduct(productId);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product: " + e.getMessage());
        }
    }

    @GetMapping("getall")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        try {
            List<ProductDto> products = productServiceImpl.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //post'ta biz json halinde atarken istekleri get'te pathvariable olarak atıyoruz
    @PostMapping("getproductid")
    public ResponseEntity<String> findProductIdByDetails(@RequestBody ProductDto productDto) {
        try {
            String productId = productServiceImpl.findProductIdByDetails(
                    productDto.getProductName(),
                    productDto.getProductDescription(),
                    productDto.getProductPrice());
            return ResponseEntity.ok(productId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("getbyproductid/{productId}")
    public ResponseEntity<ProductDto> getProductByProductId(@PathVariable String productId) {
        try {
            ProductDto product = productServiceImpl.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}