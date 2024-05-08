package com.microservicios.reservas.controladores;

import com.microservicios.reservas.dto.HotelDTO;
import com.microservicios.reservas.servicios.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/")
    public ResponseEntity<String> crearHotel(@RequestBody HotelDTO hotelDTO) {
        String mensaje = hotelService.crearHotel(hotelDTO);
        return ResponseEntity.ok(mensaje);
    }

    @PatchMapping("/")
    public ResponseEntity<String> actualizarHotel(@RequestBody HotelDTO hotelDTO) {
        String mensaje = hotelService.actualizarHotel(hotelDTO);
        return ResponseEntity.ok(mensaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHotel(@PathVariable int id) {
        String mensaje = hotelService.eliminarHotel(id);
        return ResponseEntity.ok(mensaje);
    }
}
