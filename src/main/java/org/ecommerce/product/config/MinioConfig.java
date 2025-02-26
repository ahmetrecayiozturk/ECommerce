package org.ecommerce.product.config;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MinioConfig {
    // url'si
    @Value("${minio.url}")
    private String minioUrl;
    // access key'i
    @Value("${minio.accessKey}")
    private String accessKey;
    // secret key'i
    @Value("${minio.secretKey}")
    private String secretKey;

    // MinioClient'i bean olarak atıyoruz ki autowired ile kullanabilelim
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(accessKey, secretKey)  // MinIO erişim anahtarları
                .build();
    }
}