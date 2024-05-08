package com.microservicios.reservas.dto;

import com.microservicios.reservas.model.Hotel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDTO {
    private int hotel_id;
    private String nombre;
    private String direccion;
    public HotelDTO(Hotel hotel){
        this.hotel_id=hotel.getHotel_id();
        this.nombre=hotel.getNombre();
        this.direccion=hotel.getDireccion();
    }
}
