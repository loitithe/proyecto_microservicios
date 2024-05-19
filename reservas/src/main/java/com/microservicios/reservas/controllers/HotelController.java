package com.microservicios.reservas.controllers;

import com.microservicios.reservas.dto.HotelDTO;
import com.microservicios.reservas.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("")
    public ResponseEntity<String> crearHotel(@RequestBody HotelDTO hotelDTO) {

        String mensaje = hotelService.crearHotel(hotelDTO);
        return ResponseEntity.ok(mensaje);
    }

    @PatchMapping("")
    public ResponseEntity<String> actualizarHotel(@RequestBody HotelDTO hotelDTO) {

        String mensaje = hotelService.actualizarHotel(hotelDTO);
        return ResponseEntity.ok(mensaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHotel(@PathVariable int id) {
        String mensaje = hotelService.eliminarHotel(id);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/id")
    public ResponseEntity<Integer> obtenerIdApartirNombre(@RequestParam String nombre) {
        int id = hotelService.obtenerIdApartirNombre(nombre);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/nombre")
    public ResponseEntity<String> obtenerNombreAPartirId(@RequestParam int id) {
        String nombre = hotelService.obtenerNombreAPartirId(id);
        return ResponseEntity.ok(nombre);
    }
}
