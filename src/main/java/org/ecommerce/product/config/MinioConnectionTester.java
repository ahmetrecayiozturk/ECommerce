package org.ecommerce.product.config;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
//minio'ya bağlandık mı kontrol etmek için ayrıca bir anatasyonla işaretledik ve bunu test etmek için bir metot oluşturduk
@Component
public class MinioConnectionTester {
    //MinioClient bean'i enjekte edildi
    @Autowired
    private MinioClient minioClient;
    //MinioClient bean'i ile bağlantı testi yapılıyoruz
    @PostConstruct
    public void testMinioConnection() {
        try {
            List<String> bucketNames = minioClient.listBuckets().stream()
                    .map(bucket -> bucket.name())
                    .toList();
            System.out.println("Connected to MinIO. Buckets: " + bucketNames);
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            System.err.println("Error connecting to MinIO: " + e.getMessage());
        }
    }
}
