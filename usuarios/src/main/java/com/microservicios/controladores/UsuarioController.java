package com.microservicios.controladores;

import com.microservicios.entidades.Usuario;
import com.microservicios.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<String> testEndpoint() {
        System.out.println("test");
        return ResponseEntity.ok("¡El endpoint de prueba funciona correctamente!");
    }
    @PostMapping("/register")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        System.out.println("pasa");
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUser(usuario));
    }
}
