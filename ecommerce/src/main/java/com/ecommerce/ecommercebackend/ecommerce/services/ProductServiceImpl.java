package com.ecommerce.ecommercebackend.ecommerce.services;

import com.ecommerce.ecommercebackend.ecommerce.dto.ProductDto;
import com.ecommerce.ecommercebackend.ecommerce.entity.Product;
import com.ecommerce.ecommercebackend.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.ecommercebackend.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//loggerlar ver execptionslar ile error handling daha rahat olacaktır
//service katmanı repository ve dto ile iletişimdedir
@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(String productId) {
        logger.info("Fetching product with ID: {}", productId);
        //modelmapper kullanılıyor
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with productId " + productId));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        logger.info("Product saved with ID: {}", savedProduct.getProductId());
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto updatedProductDto) {
        logger.info("Updating product with ID: {}", productId);
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with productId " + productId));

        modelMapper.map(updatedProductDto, product);

        product.setProductCreatedDate(product.getProductCreatedDate());
        product.setId(product.getId());

        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(String productId) {
        logger.info("Deleting product with ID: {}", productId);
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with productId " + productId));
        productRepository.delete(product);
    }

    public String findProductIdByDetails(String productName, String productDescription, double productPrice) {
        logger.info("Finding product ID with details - Name: {}, Description: {}, Price: {}", productName, productDescription, productPrice);
        Optional<Product> product = productRepository.findIdByProductNameAndProductDescriptionAndProductPrice(productName, productDescription, productPrice);
        if (product.isPresent()) {
            return product.get().getProductId();
        } else {
            throw new ProductNotFoundException("Product not found with given details");
        }
    }
}