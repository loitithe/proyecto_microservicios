package com.microservicios.reservas.controllers;

import com.microservicios.reservas.dto.CrearReservaDTO;
import com.microservicios.reservas.dto.ReservaDTO;
import com.microservicios.reservas.services.HabitacionService;
import com.microservicios.reservas.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PatchMapping("")
    public ResponseEntity<String> cambiarEstadoReserva(@RequestBody ReservaDTO cambioEstadoReservaDTO) {
        String mensaje = reservaService.cambiarEstadoReserva(cambioEstadoReservaDTO);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("")
    public ResponseEntity<List<ReservaDTO>> listarReservasUsuario(@RequestParam String usuario, @RequestParam String contraseña) {
        List<ReservaDTO> reservas = reservaService.listarReservasUsuario(usuario, contraseña);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("")
    public ResponseEntity<List<ReservaDTO>> listarReservasSegunEstado(@RequestParam String estado) {
        List<ReservaDTO> reservas = reservaService.listarReservasSegunEstado(estado);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkReserva(@RequestParam int idUsuario, @RequestParam int idHotel, @RequestParam int idReserva) {
        boolean existeReserva = reservaService.checkReserva(idUsuario, idHotel, idReserva);
        return ResponseEntity.ok(existeReserva);
    }
}
