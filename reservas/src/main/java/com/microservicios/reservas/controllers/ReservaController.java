package com.microservicios.reservas.controllers;

import com.microservicios.reservas.dto.*;
import com.microservicios.reservas.services.HabitacionService;
import com.microservicios.reservas.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
//    @Autowired
//    private Usuario usuario;

    @Autowired
    private HabitacionService habitacionService;

    @PostMapping()
    public ResponseEntity<String> crearReserva(@RequestBody CrearReservaDTO reservaDTO) {
        String mensaje = reservaService.crearReserva(reservaDTO);
        return ResponseEntity.ok(mensaje);
    }

    @PatchMapping()
    public ResponseEntity<String> cambiarEstadoReserva(@RequestBody ReservaCambiarEstadoDTO cambioEstadoReservaDTO) {
        String mensaje = reservaService.cambiarEstadoReserva(cambioEstadoReservaDTO);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping()
    public ResponseEntity<List<ListarReservasDTO>> listarReservasUsuario(@RequestBody ValidarUsuarioDTO validarUsuarioDTO) {
        List<ListarReservasDTO> reservas = reservaService.listarReserva(validarUsuarioDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservas);
    }

    @GetMapping("/{estado}")
    public ResponseEntity<List<ListarReservasDTO>> listarReservasEstado(@RequestParam String estado, @RequestBody ValidarUsuarioDTO validarUsuarioDTO) {
        if (reservaService.comprobarContrasena(validarUsuarioDTO.getNombre(), validarUsuarioDTO.getContrasena())) {
            List<ListarReservasDTO> reservas = reservaService.findbyEstado(estado);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservas);
        } else return null;
    }


    @GetMapping("/check")
    public ResponseEntity<Boolean> checkReserva(@RequestParam int idUsuario, @RequestParam int idHotel, @RequestParam int idReserva) {
        boolean existeReserva = reservaService.checkReserva(idUsuario, idHotel, idReserva);
        return ResponseEntity.ok(existeReserva);
    }
}
