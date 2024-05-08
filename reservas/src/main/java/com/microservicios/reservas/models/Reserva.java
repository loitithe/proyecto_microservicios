package com.microservicios.reservas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reserva_id;

    private int usuario_id;
    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String estado;
}
