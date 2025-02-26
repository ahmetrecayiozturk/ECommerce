package org.ecommerce.product.dto;

import com.mongodb.lang.Nullable;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

//güvenlik açığı vermemek için bu katmanı oluşturuyoruz, service ve controller ile burası iletişime geçecek
//burası ileyse entity iletişime geçecek
@Data//lombok ile getter setter yapılıyor
public class ProductDto {

    @NotBlank(message = "Product name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
    private String productName;

    @NotBlank(message = "Category name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
    private String category;

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @NotBlank(message = "Product description cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
    private String productDescription;

    @Min(0)
    @NotNull(message = "Product price cannot be null")
    private double productPrice;

    @Nullable
    private LocalDateTime productCreatedDate;

    @NotBlank(message = "imageUrl cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
    private List<String> imageUrl;

}
