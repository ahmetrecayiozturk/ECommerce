package org.ecommerce.product.dto;

import lombok.Data;


public class UpdateProductRequest {

    private String id;
    private ProductDto productDto;
    public String getProductId() {
        return id;
    }

    public void setProductId(String id) {
        this.id = id;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }


}
