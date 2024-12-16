package org.ecommerce.product.service;

import org.ecommerce.product.dto.ProductDto;
import org.ecommerce.product.entity.Product;
import org.ecommerce.exception.GlobalExceptionHandler;
import org.ecommerce.product.exception.ProductNotFoundException;
import org.ecommerce.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @Override
    //transactional anatasyonu bizim birden çok repository ile çalışmamıza olanak verir
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
          List<Product> products = productRepository.findAll();
          return products.stream()
                   .map(product -> modelMapper.map(product, ProductDto.class))
                   .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProductById(String productId) {
            logger.info("Fetching product with ID: {}", productId);
            //modelmapper kullanılıyor
            Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> {
                    logger.error("Product not found with productId: {}", productId);
                    return new ProductNotFoundException("Product not found with productId " + productId);
                });
            return modelMapper.map(product, ProductDto.class);
    }

    @Override
    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
            Product product = modelMapper.map(productDto, Product.class);
            Product savedProduct = productRepository.save(product);
            logger.info("Product saved with ID: {}", savedProduct.getProductId());
            return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(String productId, ProductDto updatedProductDto) {
            logger.info("Updating product with ID: {}", productId);
            Product product = productRepository.findByProductId(productId)
                    .orElseThrow(() -> {
                        logger.error("Product not found with productId: {}", productId);
                        return new ProductNotFoundException("Product not found with productId " + productId);
                    });

            modelMapper.map(updatedProductDto, product);

            product.setProductCreatedDate(product.getProductCreatedDate());
            product.setId(product.getId());

            Product updatedProduct = productRepository.save(product);
            return modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    @Transactional
    public void deleteProduct(String productId) {
            logger.info("Deleting product with ID: {}", productId);
            Product product = productRepository.findByProductId(productId)
                    .orElseThrow(() -> {
                        logger.error("Product not found with productId: {}", productId);
                        return new ProductNotFoundException("Product not found with productId " + productId);
                    });
            productRepository.delete(product);
    }

    @Override
    @Transactional
    public String findProductIdByDetails(String productName, String productDescription, double productPrice) {
        logger.info("Finding product ID with details - Name: {}, Description: {}, Price: {}", productName, productDescription, productPrice);
        Optional<Product> product = productRepository.findIdByProductNameAndProductDescriptionAndProductPrice(productName, productDescription, productPrice);
        if (product.isPresent()) {
            return product.get().getProductId();
        } else {
            logger.error("Product not found with given details - Name: {}, Description: {}, Price: {}", productName, productDescription, productPrice);
            throw new ProductNotFoundException("Product not found with given details");
        }
    }
}