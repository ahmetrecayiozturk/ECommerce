package org.ecommerce.payment.config;

import com.iyzipay.Options;
import org.ecommerce.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class IyzicoConfig {

    @Value("${iyzico.apiKey}")
    private String apiKey;

    @Value("${iyzico.secretKey}")
    private String secretKey;

    @Value("${iyzico.baseUrl}")
    private String baseUrl;

    @Bean
    public Options iyzicoOptions() {
        Options options = new Options();
        options.setApiKey(this.apiKey);
        options.setSecretKey(this.secretKey);
        options.setBaseUrl(this.baseUrl);
        return options;
    }
}