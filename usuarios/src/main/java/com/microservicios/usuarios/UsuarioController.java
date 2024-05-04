package com.microservicios.usuarios;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        System.out.println("pasa");
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUser(usuario));
    }

    @PostMapping("/remove")
    public ResponseEntity<String>eliminarUsuario(@RequestParam int id){
        usuarioService.deleteUser(usuarioService.findById(id));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario eliminado");
    }
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        System.out.println("test");
        return ResponseEntity.ok("¡El endpoint de prueba funciona correctamente!");
    }

}
