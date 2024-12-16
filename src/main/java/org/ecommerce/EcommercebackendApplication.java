package org.ecommerce;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommercebackendApplication {

	public static void main(String[] args) {
		// Dotenv kullanarak çevresel değişkenlerin uygulama başlarken yüklenmesi, bunu .gitignore'ye ekleyeceğiz
		Dotenv dotenv = Dotenv.configure().directory("./src/main/resources").load();
		System.setProperty("DB_URI", dotenv.get("DB_URI"));
		SpringApplication.run(EcommercebackendApplication.class, args);
	}
}