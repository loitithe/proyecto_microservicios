package com.microservicios.usuarios;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/test")
@RestController
public class TestController {
    @GetMapping
    public ResponseEntity<String> testEndpoint() {
        System.out.println("test");
        return ResponseEntity.ok("¡El endpoint de prueba funciona correctamente!");
    }
}
