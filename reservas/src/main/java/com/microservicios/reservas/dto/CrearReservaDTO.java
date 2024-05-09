package com.microservicios.reservas.dto;

import com.microservicios.reservas.models.Reserva;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class CrearReservaDTO {
    private String fecha_inicio;
    private String fecha_fin;
    private int habitacion_id;

    public CrearReservaDTO(Reserva reserva){
        this.fecha_inicio = reserva.getFecha_inicio().toString();
        this.fecha_fin = reserva.getFecha_fin().toString();
        this.habitacion_id= reserva.getHabitacion().getId();
    }
}
