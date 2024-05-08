package com.microservicios.reservas.controladores;

import com.microservicios.reservas.dto.HabitacionDTO;
import com.microservicios.reservas.model.Habitacion;
import com.microservicios.reservas.servicios.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habitacion")
public class HabitacionContrller {

    private final HabitacionService habitacionService;

    @Autowired
    public HabitacionContrller(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping("")
    public ResponseEntity<String> crearHabitacion(@RequestBody HabitacionDTO habitacionDTO) {
        String mensaje = habitacionService.crearHabitacion(habitacionDTO);
        return ResponseEntity.ok(mensaje);
    }

    @PatchMapping("")
    public ResponseEntity<String> actualizarHabitacion(@RequestBody HabitacionDTO habitacionDTO) {
        String mensaje = habitacionService.actualizarHabitacion(habitacionDTO);
        return ResponseEntity.ok(mensaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable int id) {
        String mensaje = habitacionService.eliminarHabitacion(id);
        return ResponseEntity.ok(mensaje);
    }
}
