package com.microservicios.reservas.dto;

import com.microservicios.reservas.models.Hotel;
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
    public HotelDTO(String nombre,String direccion){

        this.nombre=nombre;
        this.direccion=direccion;
    }
}
