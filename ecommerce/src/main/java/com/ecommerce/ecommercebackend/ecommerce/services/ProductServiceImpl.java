package com.ecommerce.ecommercebackend.ecommerce.services;

import com.ecommerce.ecommercebackend.ecommerce.dto.ProductDto;
import com.ecommerce.ecommercebackend.ecommerce.entity.Product;
import com.ecommerce.ecommercebackend.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
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
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with productId " + productId));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto updatedProductDto) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with productId " + productId));

        modelMapper.map(updatedProductDto, product);

        product.setProductCreatedDate(product.getProductCreatedDate());
        product.setId(product.getId());

        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with productId " + productId));
        productRepository.delete(product);
    }

    public ProductDto findProductByDetails(String productName, String productDescription, double productPrice) {
        Optional<Product> product = productRepository.findByProductNameAndProductDescriptionAndProductPrice(productName, productDescription, productPrice);
        if (product.isPresent()) {
            return modelMapper.map(product.get(), ProductDto.class);
        } else {
            throw new RuntimeException("Product not found with given details");
        }
    }

    public String findProductIdByDetails(String productName, String productDescription, double productPrice) {
        Optional<Product> product = productRepository.findByProductNameAndProductDescriptionAndProductPrice(productName, productDescription, productPrice);
        if (product.isPresent()) {
            return product.get().getProductId();
        } else {
            throw new RuntimeException("Product not found with given details");
        }
    }
}