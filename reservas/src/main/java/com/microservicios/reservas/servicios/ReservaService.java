package com.microservicios.reservas.servicios;

import com.microservicios.reservas.dto.CrearReservaDTO;
import com.microservicios.reservas.dto.ReservaDTO;
import com.microservicios.reservas.model.Habitacion;
import com.microservicios.reservas.model.Reserva;
import com.microservicios.reservas.repositorios.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ReservaService {

    @Autowired
    private final IReservaRepository reservaRepository;
    @Autowired
    private final HabitacionService habitacionService;

    @Autowired
    public ReservaService(IReservaRepository reservaRepository, HabitacionService habitacionService) {
        this.reservaRepository = reservaRepository;
        this.habitacionService = habitacionService;
    }

    public String crearReserva(CrearReservaDTO reservaDTO) {
        try {
            // Verificar si la habitación existe
            Reserva reserva = new Reserva();
            // Ejemplo de fecha en formato String

            // Definir el formato esperado de la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Convertir el String a LocalDate
            String fechaInicio = reservaDTO.getFecha_inicio();
            String fechaFin = reservaDTO.getFecha_fin();
            LocalDate fecha = LocalDate.parse(fechaInicio, formatter);
            reserva.setFecha_inicio(fecha);
            fecha = LocalDate.parse(fechaFin, formatter);
            reserva.setFecha_fin(fecha);
            reserva.setHabitacion(habitacionService.findById(reservaDTO.getHabitacion_id()));
            reservaRepository.save(reserva);
            return "Reserva creada correctamente";
        } catch (Exception e) {
            return "Error al crear la reserva: " + e.getMessage();
        }
    }

    public String cambiarEstadoReserva(ReservaDTO cambioEstadoReservaDTO) {

        return "";
    }
}
