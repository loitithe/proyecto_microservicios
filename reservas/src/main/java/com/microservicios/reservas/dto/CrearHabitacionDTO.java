package com.microservicios.reservas.dto;

import com.microservicios.reservas.models.Habitacion;
import com.microservicios.reservas.models.Hotel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CrearHabitacionDTO {

    private int hotel_id;
    private int numero_habitacion;
    private String tipo ;
    private BigDecimal precio;
    private CrearHabitacionDTO(Habitacion habitacion){
        this.hotel_id= habitacion.getHotel().getHotel_id();
        this.numero_habitacion= habitacion.getNumero_habitacion();
        this.tipo= habitacion.getTipo();
        this.precio= habitacion.getPrecio();
    }
}
