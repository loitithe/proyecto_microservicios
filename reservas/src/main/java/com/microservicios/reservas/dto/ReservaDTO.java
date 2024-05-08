package com.microservicios.reservas.dto;

import com.microservicios.reservas.model.Habitacion;
import com.microservicios.reservas.model.Reserva;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservaDTO {
    private int reservaId;
    private int usuarioId;
    private Habitacion habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    public ReservaDTO(Reserva reserva) {
        this.reservaId = reserva.getReserva_id();
        this.usuarioId = reserva.getReserva_id();
        this.habitacion = reserva.getHabitacion();
        this.fechaInicio = reserva.getFecha_inicio();
        this.fechaFin = reserva.getFecha_fin();
        this.estado = reserva.getEstado();
    }
}