//----try cath kaldırılmış hali----
package com.ecommerce.ecommercebackend.ecommerce.controllers;

import com.ecommerce.ecommercebackend.ecommerce.dto.ProductDto;
import com.ecommerce.ecommercebackend.ecommerce.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//controller katmanı service ve dto ile iletişimdedir
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto savedProduct = productServiceImpl.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @Valid @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productServiceImpl.updateProduct(productId, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@Valid @RequestBody ProductDto productDto) {
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


    @GetMapping("/getall")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productServiceImpl.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/getproductid")
    public ResponseEntity<String> findProductIdByDetails(@Valid @RequestBody ProductDto productDto) {
        String productId = productServiceImpl.findProductIdByDetails(
                productDto.getProductName(),
                productDto.getProductDescription(),
                productDto.getProductPrice());
        return ResponseEntity.ok(productId);
    }

    @GetMapping("/getproductbyid/{productId}")
    public ResponseEntity<ProductDto> getProductByProductId(@PathVariable String productId) {
        ProductDto product = productServiceImpl.getProductById(productId);
        return ResponseEntity.ok(product);
    }
}
/*----try cath ekli hali------
package com.ecommerce.ecommercebackend.ecommerce.controllers;

import com.ecommerce.ecommercebackend.ecommerce.dto.ProductDto;
import com.ecommerce.ecommercebackend.ecommerce.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//controller katmanı service ve dto ile iletişimdedir
@RestController
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping("save")
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            ProductDto savedProduct = productServiceImpl.addProduct(productDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @Valid @RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProduct = productServiceImpl.updateProduct(productId, productDto);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@Valid @RequestBody ProductDto productDto) {
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

    @PostMapping("getproductid")
    public ResponseEntity<String> findProductIdByDetails(@Valid @RequestBody ProductDto productDto) {
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

    @GetMapping("getproductbyid/{productId}")
    public ResponseEntity<ProductDto> getProductByProductId(@PathVariable String productId) {
        try {
            ProductDto product = productServiceImpl.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
*/