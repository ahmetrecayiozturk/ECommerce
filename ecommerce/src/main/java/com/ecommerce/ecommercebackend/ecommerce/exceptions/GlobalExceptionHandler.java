package com.ecommerce.ecommercebackend.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//controlleradvice jpring tarafından otomatik olarak oluşturulure istisnai hata durumlarını inceler, gpt tanımı ile
/*Spring Framework'te @ControllerAdvice ile tanımlanan bir Global Exception Handler (Global Hata İşleyici),
otomatik olarak Spring MVC tarafından yönetilir ve yalnızca belirli koşullarda devreye girer.
Manuel olarak çağrılması, tasarım açısından hatalı bir yaklaşımdır, çünkü bu sınıfın amacı elle çağrılmak değil,
Spring'in hata yönetim sürecine entegre olmaktır. @ControllerAdvice Nedir?
@ControllerAdvice, Spring MVC'nin hata işleme mekanizmasını merkezi bir şekilde ele almak için kullanılan
bir anotasyondur. Bu anotasyon, belirli bir kontrolcüde (@Controller veya @RestController) ortaya çıkan
istisnaları otomatik olarak yakalamak için tasarlanmıştır.*/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }

    //eğer özel bir exception tanımlarsak, onun için ayrı bir handler eklenebilinir
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}