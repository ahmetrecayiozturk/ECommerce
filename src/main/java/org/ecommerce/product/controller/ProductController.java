package org.ecommerce.product.controller;

import org.ecommerce.product.dto.*;
import org.ecommerce.product.exception.ProductNotFoundException;
import org.ecommerce.product.service.MinioService;
import org.ecommerce.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
//controller katmanı service ve dto ile iletişimdedir
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private MinioService minioService;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDto> saveProduct(
            @RequestPart("product") @Valid ProductDto productDto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        try {
            ProductDto savedProduct = productServiceImpl.addProduct(productDto, files);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //ürün güncelleme
    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody UpdateProductRequest updateProductRequest) {
        try {
            ProductDto updatedProduct = productServiceImpl.updateProduct(updateProductRequest.getProductId(), updateProductRequest.getProductDto());
            return ResponseEntity.ok(updatedProduct);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //ürün silme
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@Valid @RequestBody ProductDto productDto) {
        try {
            String productId = productServiceImpl.findProductIdByDetails(productDto);
            productServiceImpl.deleteProduct(productId);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting product: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product");
        }
    }

    //ürünlerin hepsini getirme her sayfada 20 tane olacak şekilde
    @GetMapping("/getallpoducts")
    public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam(defaultValue = "0") int page) {
        try {
            Pageable pageable = PageRequest.of(page, 20);
            Page<ProductDto> products = productServiceImpl.getAllProducts(pageable);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //o ürüne ait resimlerin url'lerini getirme
    @GetMapping("/getproductimageurls")
    public ResponseEntity<List<String>> getProductImages(@RequestBody ProductIdRequest productIdRequest) {
        try {
            List<String> imageUrls = minioService.getFilesByProductId(productIdRequest.getProductId());
            return ResponseEntity.ok(imageUrls);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //product detayları ile product id'si döndürme
    @PostMapping("/getproductidbydetails")
    public ResponseEntity<String> findProductIdByDetails(@Valid @RequestBody ProductDto productDto) {
        try {
            String productId = productServiceImpl.findProductIdByDetails(productDto);
            return ResponseEntity.ok(productId);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //product'u id ile döndürme
    @PostMapping("/getproductbyid")
    public ResponseEntity<ProductDto> getProductByProductId(@Valid @RequestBody ProductIdRequest productIdRequest) {
        try {
            ProductDto product = productServiceImpl.getProductById(productIdRequest.getProductId());
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //kategoriye göre sıralanmış productları getirme
    @GetMapping("/getproductsbycategory")
    public ResponseEntity<Page<ProductDto>> getProductByCategory(@RequestBody CategoryRequest categoryRequest,
                                                                 @RequestParam(defaultValue = "0") int page) {
        try {
            Pageable pageable = PageRequest.of(page, 20);
            Page<ProductDto> products = productServiceImpl.listProductsByCategory(categoryRequest.getCategory(), pageable);
            return ResponseEntity.ok(products);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //filtre ve sıralamaya göre productları getirme
    @GetMapping("/getproductsbyfilterandsort")
    public ResponseEntity<Page<ProductDto>> getProductByFilterAndSort(@RequestBody FilterRequest filterRequest,
                                                                      @RequestParam(defaultValue = "0") int page) {
        try {
            Pageable pageable = PageRequest.of(page, 20);
            Page<ProductDto> products = productServiceImpl.listProductsBySortOrder(filterRequest.getSortOrder(), pageable);
            return ResponseEntity.ok(products);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}