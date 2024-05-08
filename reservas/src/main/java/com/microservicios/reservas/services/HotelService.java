package com.microservicios.reservas.servicios;

import com.microservicios.reservas.dto.HotelDTO;
import com.microservicios.reservas.model.Hotel;
import com.microservicios.reservas.repositorios.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {
    private final IHotelRepository hotelRepository;

    @Autowired
    public HotelService(IHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public String crearHotel(HotelDTO hotelDTO) {
        try {
            Hotel hotel = new Hotel();
            hotel.setNombre(hotelDTO.getNombre());
            hotel.setDireccion(hotelDTO.getDireccion());
            hotelRepository.save(hotel);
            return "Hotel creado correctamente";
        } catch (Exception e) {
            return "Error al crear el hotel: " + e.getMessage();
        }
    }

    public String actualizarHotel(HotelDTO hotelDTO) {
        try {
          Hotel hotel = hotelRepository.findById(hotelDTO.getHotel_id()).orElse(null);
            if (hotel!=null) {
                hotel.setNombre(hotelDTO.getNombre());
                hotel.setDireccion(hotelDTO.getDireccion());
                hotelRepository.save(hotel);
                return "Hotel actualizado correctamente";
            } else {
                return "Hotel no encontrado";
            }
        } catch (Exception e) {
            return "Error al actualizar el hotel: " + e.getMessage();
        }
    }

    public String eliminarHotel(int id) {
        try {
           Hotel hotel = hotelRepository.findById(id).orElse(null);
            if (hotel!=null) {
                hotelRepository.delete(hotel);
                return "Hotel eliminado correctamente";
            } else {
                return "Hotel no encontrado";
            }
        } catch (Exception e) {
            return "Error al eliminar el hotel: " + e.getMessage();
        }
    }

    public String obtenerIdApartirNombre(String nombre) {
        try {
            Hotel hotel = hotelRepository.findByNombre(nombre);
            if (hotel!=null) {
                return "ID del hotel con nombre " + nombre + ": " + hotel.getHotel_id();
            } else {
                return "Hotel no encontrado con nombre " + nombre;
            }
        } catch (Exception e) {
            return "Error al obtener el ID del hotel a partir del nombre: " + e.getMessage();
        }
    }

    public String obtenerNombreAPartirId(int id) {
        try {
            Hotel hotel = hotelRepository.findById(id).orElse(null);
            if (hotel!=null) {
                return "Nombre del hotel con ID " + id + ": " + hotel.getNombre();
            } else {
                return "Hotel no encontrado con ID " + id;
            }
        } catch (Exception e) {
            return "Error al obtener el nombre del hotel a partir del ID: " + e.getMessage();
        }
    }

}