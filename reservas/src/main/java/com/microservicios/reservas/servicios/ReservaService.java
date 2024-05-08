package com.microservicios.reservas.servicios;

import com.microservicios.reservas.dto.ReservaDTO;
import com.microservicios.reservas.model.Habitacion;
import com.microservicios.reservas.model.Reserva;
import com.microservicios.reservas.repositorios.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final IReservaRepository reservaRepository;
    private final HabitacionService habitacionService;

    @Autowired
    public ReservaService(IReservaRepository reservaRepository, HabitacionService habitacionService) {
        this.reservaRepository = reservaRepository;
        this.habitacionService = habitacionService;
    }

    public String crearReserva(ReservaDTO reservaDTO) {
        try {
            // Verificar si la habitación existe
            Reserva reserva = reservaRepository.crearReserva(reservaDTO);
            if (reserva == null) {
                return "La reserva especificada no existe";
            }

            // Implementar lógica para crear la reserva aquí

            return "Reserva creada correctamente";
        } catch (Exception e) {
            return "Error al crear la reserva: " + e.getMessage();
        }
    }

    public String cambiarEstadoReserva(ReservaDTO cambioEstadoReservaDTO) {

        return  "";
    }
}
