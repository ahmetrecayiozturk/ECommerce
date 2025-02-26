package org.ecommerce.product.service;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.messages.Item;
import org.ecommerce.product.config.MinioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MinioService {

    //MinioClient bean'i enjekte edildi
    @Autowired
    private MinioClient minioClient;
    //minioConfig bean'i enjekte edildi
    @Autowired
    private MinioConfig minioConfig;
    //bucket adı
    private final String bucketName = "products";

    public List<String> uploadFiles(String productId, List<MultipartFile> files) {
        List<String> fileUrls = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String fileId = UUID.randomUUID().toString();
                String fileName = productId + "-" + fileId + ".jpg"; //her dosyanın adı productId ile başlayacak
                minioClient.putObject(                               //dosyaların adı -'e göre ayrılacak ve böylece
                        PutObjectArgs.builder()                      //productId'ye ait dosyaları kolayca bulabileceğiz
                                .bucket(bucketName)
                                .object(fileName)
                                .stream(file.getInputStream(), file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build()
                );
                fileUrls.add(getObjectUrl(bucketName, fileName)); // URL'yi listeye ekle
            }
        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }
        return fileUrls;
    }

    //productId'ye ait fileUrl'leri listeler
    public List<String> getFilesByProductId(String productId) {
        List<String> fileUrls = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .prefix(productId + "-")
                            .build()
            );

            for (Result<Item> result : results) {
                Item item = result.get();
                fileUrls.add(getObjectUrl(bucketName, item.objectName()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving files", e);
        }
        return fileUrls;
    }

    private String getObjectUrl(String bucketName, String objectName) {
        return minioConfig.getMinioUrl() + "/" + bucketName + "/" + objectName;
    }
}
