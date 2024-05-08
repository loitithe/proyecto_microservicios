package com.microservicios.reservas.controladores;

import com.microservicios.reservas.dto.ReservaDTO;
import com.microservicios.reservas.servicios.HabitacionService;
import com.microservicios.reservas.servicios.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private HabitacionService habitacionService;

    @PostMapping("/")
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        String mensaje = reservaService.crearReserva(reservaDTO);
        return ResponseEntity.ok(mensaje);
    }

    @PatchMapping("/")
    public ResponseEntity<String> cambiarEstadoReserva(@RequestBody ReservaDTO cambioEstadoReservaDTO) {
        String mensaje = reservaService.cambiarEstadoReserva(cambioEstadoReservaDTO);
        return ResponseEntity.ok(mensaje);
    }

    // Otros métodos de reserva...
}