package com.microservicios.reservas.servicios;

import com.microservicios.reservas.dto.HotelDTO;
import com.microservicios.reservas.repositorios.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    @Autowired
    private IHotelRepository hotelRepository;

    public HotelService(IHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public String crearHotel(HotelDTO hotelDTO) {
        return "";
    }

    public String actualizarHotel(HotelDTO hotelDTO) {
        return "";
    }

    public String eliminarHotel(int id) {
        return "";
    }
}
