package com.microservicios.usuarios.controlador;

import com.microservicios.usuarios.dto.UsuarioDTO;
import com.microservicios.usuarios.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * @param usuario
     * @return
     */
    @PostMapping("/registrar")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioDTO usuario) {
        if (usuarioService.findByNombreAndContrasena(usuario.getNombre(), usuario.getContrasena()) == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUser(usuario));

        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el usuario ya existe");
    }

    /**
     * http://localhost:8702/usuarios/remove?id=4
     * http://localhost:8702/usuarios/?nombre=María López&contrasena=secreto456
     * @param usuario
     * @return
     */
    @DeleteMapping("/")
    public ResponseEntity<String> eliminarUsuario(@RequestBody UsuarioDTO usuario) {
        UsuarioDTO user = usuarioService.getByNombreAndContrasena(usuario.getNombre(),usuario.getContrasena());
        if (user != null && user.getContrasena().equals(usuario.getContrasena())) {
            usuarioService.deleteUser(user.getUsuario_id());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario / contrasena incorrecta");
    }

    /**http://localhost:8702/usuarios/registrar
     * {
     *     "usuario_id":8,
     *     "nombre": "Paco Editado",
     *     "correo_electronico": "edit@example.com",
     *     "direccion": "Dirección del editad",
     *     "contrasena": "editapass"
     * }
     * @param usuarioDTO
     * @return
     */
    @PutMapping("/registrar")
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO user = usuarioService.findById(usuarioDTO.getUsuario_id());
        if (user != null) {
            usuarioService.actualizarUsuario(usuarioDTO);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }
    }

    /**
     *
     * http://localhost:8702/usuarios/validar
     * {
     *     "nombre": "Rodri",
     *     "contrasena": "password789"
     * }
     * @param usuarioDTO
     * @return
     */
    @PostMapping("/validar")
    public ResponseEntity<String> validarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        boolean validado = usuarioService.findByNombreAndContrasena(usuarioDTO.getNombre(),usuarioDTO.getContrasena());
        if (validado) {
            return ResponseEntity.ok("Usuario validado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no validado");
        }
    }
// http://localhost:8702/usuarios/info/id/?id=1
    @GetMapping("/info/id/")
    public ResponseEntity<String>getNombreUsuario(@RequestParam int id){
        UsuarioDTO usuario = usuarioService.findById(id);
        if (usuario!=null){
            return ResponseEntity.ok("El nombre del usuario con id : "+id+" es "+usuario.getNombre());

        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado ");
    }

    /**
     * no debe existir un nombre repetido ?
     * @param nombre
     * @return
     */
    @GetMapping("/info/nombre/")
    public ResponseEntity<Map<String, Object>> getIdUsuario(@RequestParam String nombre){
        List<UsuarioDTO> usuarios = usuarioService.findByNombre(nombre);
        if (usuarios != null && !usuarios.isEmpty()) {

            Map<String, Object> usuarioMap = new HashMap<>();
            for (UsuarioDTO usuario : usuarios) {
                usuarioMap.put("usuario_id", usuario.getUsuario_id());
                usuarioMap.put("nombre", usuario.getNombre());
            }
            return ResponseEntity.ok(usuarioMap);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyMap());
        }
    }

    /**
     * http://localhost:8702/usuarios/checkIfExist/?id=10
     * @param id
     * @return
     */
    @GetMapping("/checkIfExist/")
    public ResponseEntity<String>checkIfExist(@RequestParam int id){
        boolean existe = usuarioService.checkIfExists(id);
        if (existe){
            return ResponseEntity.ok(""+existe);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(""+existe);
    }
}
